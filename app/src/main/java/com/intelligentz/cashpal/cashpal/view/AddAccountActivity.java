package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.andexert.library.RippleView;
import com.google.gson.Gson;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.Util;
import com.intelligentz.cashpal.cashpal.adaptor.AccountSpinnerAdaptor;
import com.intelligentz.cashpal.cashpal.model.Account;
import com.intelligentz.cashpal.cashpal.model.AccountDetail;
import com.intelligentz.cashpal.cashpal.model.HttpClient;
import com.intelligentz.cashpal.cashpal.model.Strings;
import com.loopj.android.http.TextHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AddAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private MaterialEditText mobileTxt;
    private Spinner accountSpinner;
    SweetAlertDialog progressDialog;
    RippleView rippleView;
    Button loginBtn;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        context = this;
        accountSpinner = (Spinner) findViewById(R.id.spinner);
        accountSpinner.setAdapter(new AccountSpinnerAdaptor(this));
        accountSpinner.setOnItemSelectedListener(this);
        accountSpinner.setPrompt(Strings.getLogInSpinnerPrompt());
        mobileTxt = (MaterialEditText) findViewById(R.id.mobileTxt);
        mobileTxt.setHint(Strings.getMobileTextHint());
        mobileTxt.addValidator(new RegexpValidator(Strings.getInvalidText(),"07\\d{8}"));
        rippleView = (RippleView) findViewById(R.id.rippleView);
        RippleView.OnRippleCompleteListener listener = new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                login();
            }
        };
        Util.configureRippleView(rippleView,listener);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setText(Strings.getLogInText());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Account.setCurrentAccount(Account.accountDetailList.get(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void login(){

        if (!mobileTxt.validate()) return;
        if (Account.getCurrentAccount() == null) return;
        final SweetAlertDialog.OnSweetClickListener cancelListener = new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                HttpClient.cancel();
                sDialog.dismissWithAnimation();
            }
        };

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.setTitleText(Strings.getLogInProcessingTitle());
        progressDialog.setContentText(Strings.getSubmitTransactionProcessingDialogBody());
        progressDialog.getProgressHelper().setRimColor(R.color.colorPrimary);
        progressDialog.setCancelText(Strings.getCancelText());
        progressDialog.setCancelClickListener(cancelListener);
        progressDialog.show();
        sendHttpRequest(progressDialog);
    }

    public void sendHttpRequest(final SweetAlertDialog sweetAlertDialog){
        final SweetAlertDialog.OnSweetClickListener successListener = new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mobileTxt.setText("");
                sweetAlertDialog.dismissWithAnimation();
            }
        };
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("agent_mobile", Util.validateMobile(mobileTxt.getText().toString()));
            StringEntity entity = new StringEntity(jsonParams.toString());
            HttpClient.post(this, Account.getCurrentAccount().getOTP_REQUEST_URL(), entity, HttpClient.CONTENT_TYPE_JSON, new TextHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    sweetAlertDialog.dismissWithAnimation();
                    Intent intent = new Intent(context, OTPValidationActivity.class);
                    intent.putExtra("agent_mobile", mobileTxt.getText().toString());
                    startActivity(intent);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                    sweetAlertDialog.setTitleText(Strings.getFailedText())
                            .setContentText(Strings.getSubmitTransactionFailureDialogBody())
                            .setConfirmText(Strings.getOkText())
                            .setConfirmClickListener(successListener)
                            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
