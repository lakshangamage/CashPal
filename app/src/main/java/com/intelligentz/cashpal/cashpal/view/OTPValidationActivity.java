package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.andexert.library.RippleView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.Util;
import com.intelligentz.cashpal.cashpal.model.Account;
import com.intelligentz.cashpal.cashpal.model.AccountDetail;
import com.intelligentz.cashpal.cashpal.model.HttpClient;
import com.intelligentz.cashpal.cashpal.model.Strings;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class OTPValidationActivity extends AppCompatActivity {
    private CircularImageView imageView;
    private MaterialEditText mobileText;
    private MaterialEditText pinText;
    private RippleView rippleView;
    private Button verifyBtn;
    private SweetAlertDialog progressDialog;
    private String mobileNumber;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpvalidation);
        context = this;
        imageView = (CircularImageView) findViewById(R.id.photoView);
        imageView.setImageResource(Account.getCurrentAccount().getAccountIcon());
        mobileText = (MaterialEditText) findViewById(R.id.mobileTxt);
        mobileNumber = getIntent().getStringExtra("mobile");
        mobileText.setText(mobileNumber);
        mobileText.setHint(Strings.getMobileTextHint());
        mobileText.setEnabled(false);
        pinText = (MaterialEditText) findViewById(R.id.pinTxt);
        pinText.setHint(Strings.getAgentPinTextHint());
        rippleView = (RippleView) findViewById(R.id.rippleView);
        RippleView.OnRippleCompleteListener listener = new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                verify();
            }
        };
        Util.configureRippleView(rippleView, listener);
        verifyBtn = (Button) findViewById(R.id.verifybtn);
        verifyBtn.setText(Strings.getVerifyText());
    }

    public void verify() {
        if (pinText.getText() == null || pinText.getText().toString().isEmpty()) return;
        if (Account.getCurrentAccount() == null) return;
        final SweetAlertDialog.OnSweetClickListener cancelListener = new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                HttpClient.cancel();
                sDialog.dismissWithAnimation();
            }
        };

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.setTitleText(Strings.getVerifyProcessingTitle());
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
                pinText.setText("");
                sweetAlertDialog.dismissWithAnimation();
            }
        };
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("agentMobile", mobileNumber);
            jsonParams.put("pin", pinText.getText().toString().trim());
            StringEntity entity = new StringEntity(jsonParams.toString());
            HttpClient.post(this, Account.getCurrentAccount().getOTP_VALIDATE_URL(), entity, HttpClient.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    sweetAlertDialog.dismissWithAnimation();
                    AccountDetail account = Account.getCurrentAccount();
                    if (!account.getSubAccoutList().contains(mobileNumber)) {
                        account.getSubAccoutList().add(mobileNumber);
                        Account.setCurrentSubAccountIndex(0);
                    } else {
                        Account.setCurrentSubAccountIndex(account.getSubAccoutList().indexOf(mobileNumber));
                    }
                    Account.getCurrentActiveSubAccountList().add(mobileNumber);
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                    sweetAlertDialog.setTitleText(Strings.getFailedText())
                            .setContentText(Strings.getPinVerificationFaliureDialogBody())
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
