package com.intelligentz.cashpal.cashpal.model;

import com.intelligentz.cashpal.cashpal.R;

/**
 * Created by lakshan on 12/29/16.
 */
public class MCashDetail extends AccountDetail {
    public AccountDetail init() {
        accountIcon = R.drawable.mcashicon;
        accountContextURI = "m/";
        account_id = "m_cash";
        accountName = "mCash";
        return this;
    }
}
