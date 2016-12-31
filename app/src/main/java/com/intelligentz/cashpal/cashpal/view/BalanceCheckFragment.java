package com.intelligentz.cashpal.cashpal.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.Util;
import com.intelligentz.cashpal.cashpal.model.HttpClient;
import com.intelligentz.cashpal.cashpal.model.Strings;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class BalanceCheckFragment extends Fragment {
    View view;
    Activity activity;
    MaterialEditText pinTxt;
    Button topupbrn;
    RippleView rippleView;
    SweetAlertDialog progressDialog;
    TextView titleText;

    public BalanceCheckFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_balance_check, container, false);
        activity = getActivity();
        pinTxt = (MaterialEditText) view.findViewById(R.id.pinTxt);
        topupbrn = (Button) view.findViewById(R.id.topupbtn);
        titleText = (TextView) view.findViewById(R.id.titleTxt);
        rippleView = (RippleView) view.findViewById(R.id.rippleView);
        RippleView.OnRippleCompleteListener listener = new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                topup();
            }
        };
        Util.configureRippleView(rippleView,listener);
        setText();
        return view;
    }

    private void setText(){
        titleText.setText(Strings.getBalanceCheckTitle());
        pinTxt.setHint(Strings.getAgentPinTextHint());
        topupbrn.setText(Strings.getTopUpButtonText());
    }

    @Override
    public void onStart() {
        super.onStart();
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.titleTxt));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.pinTxt));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.topupbtn));
    }

    public void topup(){
        if (pinTxt.getText() == null || pinTxt.getText().toString().isEmpty()) return;

        final SweetAlertDialog.OnSweetClickListener cancelListener = new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                HttpClient.cancel();
                sDialog.dismissWithAnimation();
            }
        };

        progressDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
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
                sweetAlertDialog.dismissWithAnimation();
            }
        };
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("agentMobile", "773472649");
            jsonParams.put("agentPin", pinTxt.getText().toString());
            StringEntity entity = new StringEntity(jsonParams.toString());
            HttpClient.post(getContext(), "topup", entity, HttpClient.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    sweetAlertDialog.setTitleText(Strings.getSuccessText())
                            .setContentText(Strings.getSubmitTransactionSuccessDialogBody())
                            .setConfirmText(Strings.getOkText())
                            .setConfirmClickListener(successListener)
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
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
