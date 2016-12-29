package com.intelligentz.cashpal.cashpal.model;

import com.intelligentz.cashpal.cashpal.R;

/**
 * Created by lakshan on 12/29/16.
 */
public class EzCashDetail extends AccountDetail {
    public AccountDetail init() {
        accountIcon = R.drawable.ezcash_icon;
        accountContextURI = "ez/";
        accountName = "EzCash";
        account_id = "ez_cash";
        return this;
    }
}
