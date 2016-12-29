package com.intelligentz.cashpal.cashpal.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by lakshan on 12/29/16.
 */
public class Account {
    public static ArrayList<AccountDetail> accountDetailList = new ArrayList<>();
    public static final String SUB_ACCOUNT_IDENTIFIER = "sub_accounts";
    static {
        accountDetailList.add(new MCashDetail().init());
        accountDetailList.add(new EzCashDetail().init());
    }
    private static AccountDetail currentAccount;
    private static int currentSubAccountIndex;
    private static ArrayList<Strings> currentActiveSubAccountList = new ArrayList<>();

    public static void setCurrentAccount(AccountDetail currentAccount) {
        Account.currentAccount = currentAccount;

    }

    public static void setCurrentSubAccountIndex(int currentSubAccountIndex) {
        Account.currentSubAccountIndex = currentSubAccountIndex;
    }

    public static void setCurrentActiveSubAccountList(ArrayList<Strings> currentActiveSubAccountList) {
        Account.currentActiveSubAccountList = currentActiveSubAccountList;
    }

    public static AccountDetail getCurrentAccount() {
        return currentAccount;
    }

    public static int getCurrentSubAccountIndex() {
        return currentSubAccountIndex;
    }

    public static ArrayList<Strings> getCurrentActiveSubAccountList() {
        return currentActiveSubAccountList;
    }
}
