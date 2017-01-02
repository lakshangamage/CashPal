package com.intelligentz.cashpal.cashpal.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
public class PaymentFragment extends Fragment {
    View view;
    Activity activity;
    Button billPaymentButton;
    Button utilityPaymentButton;
    Button institutionalPaymentButton;
    Button receivePaymentButton;
    TextView titleText;

    public PaymentFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        activity = getActivity();
        titleText = (TextView) view.findViewById(R.id.titleTxt);
        billPaymentButton = (Button) view.findViewById(R.id.billbtn);
        utilityPaymentButton = (Button) view.findViewById(R.id.utilitybtn);
        institutionalPaymentButton = (Button) view.findViewById(R.id.institutionalbtn);
        receivePaymentButton = (Button) view.findViewById(R.id.receivebtn);
        setText();
        addButtonListeners();
        return view;
    }

    private void setText(){
        titleText.setText(Strings.getPaymentTitle());
        billPaymentButton.setText(Strings.getBillPaymentButtonText());
        utilityPaymentButton.setText(Strings.getUtilityPaymentButtonText());
        institutionalPaymentButton.setText(Strings.getInstitutionalPaymentButtonText());
        receivePaymentButton.setText(Strings.getReceivePaymentButtonText());
    }

    @Override
    public void onStart() {
        super.onStart();
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.titleTxt));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.billbtn));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.utilitybtn));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.institutionalbtn));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(view.findViewById(R.id.receivebtn));
    }

    private void addButtonListeners() {
        billPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BillPaymentActivity.class);
                startActivity(intent);
            }
        });

        utilityPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UtilityPaymentActivity.class);
                startActivity(intent);
            }
        });

        institutionalPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InstitutionalPaymentActivity.class);
                startActivity(intent);
            }
        });

        receivePaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), eceivePaymentActivity.class);
                startActivity(intent);
            }
        });
    }


}
