package com.intelligentz.cashpal.cashpal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.andexert.library.RippleView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class CustomerTopUp extends Fragment {
    View view;
    Activity activity;
    MaterialEditText pinTxt;
    MaterialEditText mobileTxt;
    MaterialEditText amountTxt;
    Button topupbrn;
    RippleView rippleView;
    SweetAlertDialog progressDialog;

    public CustomerTopUp() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer_top_up, container, false);
        activity = getActivity();
        pinTxt = (MaterialEditText) view.findViewById(R.id.pinTxt);
        mobileTxt = (MaterialEditText) view.findViewById(R.id.mobileTxt);
        amountTxt = (MaterialEditText) view.findViewById(R.id.amountTxt);
        topupbrn = (Button) view.findViewById(R.id.topupbtn);
        rippleView = (RippleView) view.findViewById(R.id.rippleView);
        mobileTxt.addValidator(new RegexpValidator("Invalid","07\\d{8}"));
        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(RippleView rippleView) {
                topup();
            }

        });
        return view;
    }


    public void topup(){
        if (pinTxt.getText() == null || pinTxt.getText().toString().isEmpty()) return;
        if (amountTxt.getText() == null || amountTxt.getText().toString().isEmpty()) return;
        if (!mobileTxt.validate()) return;

        final SweetAlertDialog.OnSweetClickListener cancelListener = new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                HttpClient.cancel();
                sDialog.dismissWithAnimation();
            }
        };

        progressDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        progressDialog.setTitleText("Submit Transaction?");
        progressDialog.setContentText("Customer: " + mobileTxt.getText().toString()+ "\nAmount: " + amountTxt.getText().toString() );
        progressDialog.setCancelText("No");
        progressDialog.setConfirmText("Yes");
        progressDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.setTitleText("Processing Request...")
                        .setContentText("This might take few seconds.")
                        .setCancelText("Cancel")
                        .setCancelClickListener(cancelListener);
                sweetAlertDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimaryDark));
                sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                sendHttpRequest(sweetAlertDialog);
            }
        });
        progressDialog.setCancelClickListener(cancelListener);
        progressDialog.show();
    }

    public void sendHttpRequest(final SweetAlertDialog sweetAlertDialog){
        final SweetAlertDialog.OnSweetClickListener successListener = new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mobileTxt.setText("");
                amountTxt.setText("");
                sweetAlertDialog.dismissWithAnimation();
            }
        };
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("agentMobile", "773472649");
            jsonParams.put("customerMobile", mobileTxt.getText().toString());
            jsonParams.put("agentPin", pinTxt.getText().toString());
            jsonParams.put("amount", amountTxt.getText().toString());
            StringEntity entity = new StringEntity(jsonParams.toString());
            HttpClient.post(getContext(), "topup", entity, HttpClient.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    sweetAlertDialog.setTitleText("Success!")
                    .setContentText("Your transaction request sent successfully.")
                    .setConfirmText("OK")
                    .setConfirmClickListener(successListener)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    sweetAlertDialog.setTitleText("Failed!")
                            .setContentText("Something went wrong. Please try again.")
                            .setConfirmText("OK")
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
