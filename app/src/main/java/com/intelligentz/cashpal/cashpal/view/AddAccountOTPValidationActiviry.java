package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.andexert.library.RippleView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.Util;
import com.intelligentz.cashpal.cashpal.model.Account;
import com.intelligentz.cashpal.cashpal.model.AccountDetail;
import com.intelligentz.cashpal.cashpal.model.HttpClient;
import com.intelligentz.cashpal.cashpal.model.Strings;
import com.loopj.android.http.TextHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AddAccountOTPValidationActiviry extends AppCompatActivity {
    private CircularImageView imageView;
    private MaterialEditText mobileText;
    private MaterialEditText pinText;
    private RippleView rippleView;
    private Button verifyBtn;
    private SweetAlertDialog progressDialog;
    private String mobileNumber;
    private int selectedAccount;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_otpvalidation_activiry);
        context = this;
        imageView = (CircularImageView) findViewById(R.id.photoView);
        imageView.setImageResource(Account.getCurrentAccount().getAccountIcon());
        mobileText = (MaterialEditText) findViewById(R.id.mobileTxt);
        mobileNumber = getIntent().getStringExtra("agent_mobile");
        selectedAccount = getIntent().getIntExtra("selectedAccount",0);
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
    @Override
    protected void onResume() {
        super.onResume();
        String msg = Strings.getOtpValidationGuideMessage();
        NiftyNotificationView.build(this, msg, Effects.thumbSlider,R.id.mLyout)
                .setIcon(R.drawable.cashpal_icon).show();
    }

    public void verify() {
        if (pinText.getText() == null || pinText.getText().toString().isEmpty()) return;
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
            jsonParams.put("agent_mobile", Util.validateMobile(mobileNumber));
            jsonParams.put("otp", pinText.getText().toString().trim());
            StringEntity entity = new StringEntity(jsonParams.toString());
            HttpClient.post(this, Account.getCurrentAccount().getOTP_VALIDATE_URL(), entity, HttpClient.CONTENT_TYPE_JSON, new TextHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                    if (jsonObject.get("authenticated").getAsBoolean()) {
                        sweetAlertDialog.dismissWithAnimation();
                        AccountDetail account = Account.accountDetailList.get(selectedAccount);
                        if (!account.getSubAccoutList().contains(mobileNumber)){
                            account.getSubAccoutList().add(mobileNumber);
                            Account.setCurrentSubAccountIndex(account.getSubAccoutList().size()-1);
                            SharedPreferences mPrefs = context.getSharedPreferences(account.getAccount_id(), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(account.getSubAccoutList());
                            editor.putString(Account.SUB_ACCOUNT_IDENTIFIER, json);
                            editor.commit();
                        } else {
                            Account.setCurrentSubAccountIndex(account.getSubAccoutList().indexOf(mobileNumber));
                        }
                        Account.getCurrentActiveSubAccountList().add(mobileNumber);
                        Account.setCurrentAccount(account);
                        Account.isAccountChanged = true;
                        finish();
                    } else {
                        sweetAlertDialog.setTitleText(Strings.getFailedText())
                                .setContentText(Strings.getPinVerificationFaliureDialogBody())
                                .setConfirmText(Strings.getOkText())
                                .setConfirmClickListener(successListener)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
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
