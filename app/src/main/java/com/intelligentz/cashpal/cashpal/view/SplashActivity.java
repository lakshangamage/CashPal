package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.model.Strings;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences mPrefs = getSharedPreferences("cashpal.language", Context.MODE_PRIVATE);
        String language = mPrefs.getString("language",null);
        if (language == null) {
            Intent intent = new Intent(this, LanguageSelectionActivity.class);
            startActivity(intent);
            finish();
        }else {
            if (language.equals("english")) {
                Strings.English.setLanguage();
            } else {
                Strings.Sinhala.setLanguage();
            }
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
