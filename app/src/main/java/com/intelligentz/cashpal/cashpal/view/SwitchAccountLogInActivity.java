package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.andexert.library.RippleView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.Util;
import com.intelligentz.cashpal.cashpal.adaptor.AccountSpinnerAdaptor;
import com.intelligentz.cashpal.cashpal.model.Account;
import com.intelligentz.cashpal.cashpal.model.HttpClient;
import com.intelligentz.cashpal.cashpal.model.Strings;
import com.loopj.android.http.TextHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SwitchAccountLogInActivity extends AppCompatActivity {
    private MaterialEditText mobileTxt;
    SweetAlertDialog progressDialog;
    CircularImageView imageView;
    RippleView rippleView;
    Button loginBtn;
    Context context;
    int selectedAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_account_log_in);
        context = this;
        selectedAccount = getIntent().getIntExtra("selectedAccount",0);
        imageView = (CircularImageView) findViewById(R.id.photoView);
        imageView.setImageResource(Account.accountDetailList.get(selectedAccount).getAccountIcon());
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
        if (Account.getCurrentActiveSubAccountList().contains(mobileTxt.getText().toString())
                || Account.accountDetailList.get(selectedAccount).getSubAccoutList().contains(mobileTxt.getText().toString())) {
            String msg = Strings.getAlreadyLoggedInMessage();
            NiftyNotificationView.build(this, msg, Effects.thumbSlider,R.id.mLyout)
                    .setIcon(R.drawable.cashpal_icon).show();
            return;
        }
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
                    Intent intent = new Intent(context, AddAccountOTPValidationActiviry.class);
                    intent.putExtra("selectedAccount",selectedAccount);
                    intent.putExtra("agent_mobile", mobileTxt.getText().toString());
                    startActivity(intent);
                    finish();
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
