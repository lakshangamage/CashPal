package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.andexert.library.RippleView;
import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.Util;
import com.intelligentz.cashpal.cashpal.model.Strings;

import co.ceryle.radiorealbutton.library.RadioRealButton;
import co.ceryle.radiorealbutton.library.RadioRealButtonGroup;

public class LanguageChangeActivity extends AppCompatActivity {
    RadioRealButton sinhalaBtn;
    RadioRealButton englishBtn;
    RadioRealButtonGroup group;
    Button proceedButton;
    RippleView rippleView;
    int selectedlanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change);
        englishBtn = (RadioRealButton) findViewById(R.id.englishBtn);
        sinhalaBtn= (RadioRealButton) findViewById(R.id.sinhalaBtn);
        group = (RadioRealButtonGroup) findViewById(R.id.buttongroup);
        proceedButton = (Button) findViewById(R.id.proceedBtn);
        proceedButton.setText(Strings.getSetText());
        rippleView = (RippleView) findViewById(R.id.rippleView);
        RippleView.OnRippleCompleteListener listener = new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                proceed();
            }
        };
        Util.configureRippleView(rippleView,listener);

        group.setOnClickedButtonPosition(new RadioRealButtonGroup.OnClickedButtonPosition() {
            @Override
            public void onClickedButtonPosition(int position) {
                selectedlanguage = position;
                if (position == 0) {
                    Strings.English.setLanguage();
                } else {
                    Strings.Sinhala.setLanguage();
                }
                proceedButton.setText(Strings.getProceedText());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String msg = "Select your preferred Language.";
        NiftyNotificationView.build(this, msg, Effects.thumbSlider,R.id.mLyout)
                .setIcon(R.drawable.cashpal_icon).show();
    }

    public void proceed() {
        SharedPreferences mPrefs = getSharedPreferences("cashpal.language",Context.MODE_PRIVATE);
        SharedPreferences.Editor preEditor = mPrefs.edit();
        if (selectedlanguage == 0) {
            preEditor.putString("language","english");
        } else {
            preEditor.putString("language","sinhala");
        }
        preEditor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
