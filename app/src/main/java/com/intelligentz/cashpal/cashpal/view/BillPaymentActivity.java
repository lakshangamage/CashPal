package com.intelligentz.cashpal.cashpal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.adaptor.AccountSpinnerAdaptor;
import com.intelligentz.cashpal.cashpal.model.Strings;
import com.rengwuxian.materialedittext.MaterialEditText;

public class BillPaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView titleText;
    private Spinner spinner;
    private MaterialEditText pinTxt;
    private MaterialEditText mobileTxt;
    private MaterialEditText amountTxt;
    private Button paymentBtn;
    private RippleView rippleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);
        init_Ui();
        setText();
    }

    private void init_Ui() {
        titleText = (TextView) findViewById(R.id.titleTxt);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new AccountSpinnerAdaptor(this));
        spinner.setOnItemSelectedListener(this);
        pinTxt = (MaterialEditText) findViewById(R.id.pinTxt);
        mobileTxt = (MaterialEditText) findViewById(R.id.pinTxt);
        amountTxt = (MaterialEditText) findViewById(R.id.amountTxt);
        paymentBtn = (Button) findViewById(R.id.paymentbtn);
        rippleView = (RippleView) findViewById(R.id.rippleView);
    }

    public void setText() {
        titleText.setText(Strings.getBillPaymentButtonText());
        spinner.setPrompt(Strings.getBillTypeText());
        pinTxt.setHint(Strings.getAgentPinTextHint());
        mobileTxt.setHint(Strings.getAccountNoText());
        amountTxt.setHint(Strings.getAmountTextHint());
        paymentBtn.setText(Strings.getPayText());
    }

    @Override
    public void onStart() {
        super.onStart();
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(findViewById(R.id.titleTxt));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(findViewById(R.id.pinTxt));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(findViewById(R.id.mobileTxt));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(findViewById(R.id.amountTxt));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(findViewById(R.id.paymentbtn));
        YoYo.with(Techniques.FlipInX)
                .duration(1200)
                .playOn(findViewById(R.id.spinner));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
