package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.model.Account;
import com.intelligentz.cashpal.cashpal.model.AccountDetail;
import com.intelligentz.cashpal.cashpal.model.Strings;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadAccounts();
        SharedPreferences mPrefs = getSharedPreferences("cashpal.language", Context.MODE_PRIVATE);
        String language = mPrefs.getString("language", null);
        if (language == null) {
            Intent intent = new Intent(this, LanguageSelectionActivity.class);
            startActivity(intent);
            finish();
        } else {
            if (language.equals("english")) {
                Strings.English.setLanguage();
            } else {
                Strings.Sinhala.setLanguage();
            }

            Intent intent;
            if (Account.getCurrentAccount().getSubAccoutList() != null &&
                    !Account.getCurrentAccount().getSubAccoutList().isEmpty()) {
                Account.setCurrentSubAccountIndex(0);
                intent = new Intent(this, MainActivity.class);
            } else {
                intent = new Intent(this, LogInActivity.class);
            }
            startActivity(intent);
            finish();
        }
    }

    private void loadAccounts() {
        for (int i=0; i<Account.accountDetailList.size(); i++) {
            AccountDetail acc = Account.accountDetailList.get(i);
            SharedPreferences mPrefs = getSharedPreferences(acc.getAccount_id(), Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString(Account.SUB_ACCOUNT_IDENTIFIER, null);
            if (json != null && !json.isEmpty()) {
                ArrayList<String> persistedList = gson.fromJson(json, ArrayList.class);
                if (persistedList != null && !persistedList.isEmpty()) {
                    acc.getSubAccoutList().clear();
                    for (String id : persistedList) {
                        acc.addSubAccountToList(id);
                    }
                    Account.setCurrentAccount(acc);
                }
            }
        }
        if (Account.getCurrentAccount() == null) {
            Account.setCurrentAccount(Account.accountDetailList.get(1));
        }
    }

//    private void loadAccounts() {
//        for (int i = 0; i< Account.accountDetailList.size(); i++) {
//            AccountDetail acc = Account.accountDetailList.get(i);
//            SharedPreferences mPrefs = getSharedPreferences(acc.getAccount_id(), Context.MODE_PRIVATE);
//            Gson gson = new Gson();
//            String json = mPrefs.getString(Account.SUB_ACCOUNT_IDENTIFIER, null);
//            if (json != null && !json.isEmpty()) {
//                ArrayList<String> persistedList = gson.fromJson(json, ArrayList.class);
//                if (persistedList != null && !persistedList.isEmpty()) {
//                    acc.getSubAccoutList().clear();
//                    for (String id : persistedList) {
//                        acc.addSubAccountToList(id);
//                    }
//                }
//            }
//            Account.setCurrentAccount(acc);
//        }
//        AccountDetail account = Account.getCurrentAccount();
//        String mobileNumber = "0773472649";
//        if (!account.getSubAccoutList().contains(mobileNumber)) {
//            account.getSubAccoutList().add(mobileNumber);
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            account.getSubAccoutList().add("0771560046");
//            Account.setCurrentSubAccountIndex(0);
//        } else {
//            Account.setCurrentSubAccountIndex(account.getSubAccoutList().indexOf(mobileNumber));
//        }
//        Account.getCurrentActiveSubAccountList().add(mobileNumber);
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}
