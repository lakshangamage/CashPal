package com.intelligentz.cashpal.cashpal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.adaptor.AccountSpinnerAdaptor;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LogInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private MaterialEditText mobileTxt;
    private Spinner accountSpinner;
    int selectedAccountPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        accountSpinner = (Spinner) findViewById(R.id.spinner);
        accountSpinner.setAdapter(new AccountSpinnerAdaptor(this));
        accountSpinner.setOnItemSelectedListener(this);
        mobileTxt = (MaterialEditText) findViewById(R.id.mobileTxt);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedAccountPosition = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
