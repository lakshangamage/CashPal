package com.intelligentz.cashpal.cashpal.model;

import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.model.Strings;

import java.util.ArrayList;

/**
 * Created by lakshan on 12/29/16.
 */
public abstract class AccountDetail {
    public static String ACCOUNT_TYPE_EZ_CASH = "ez_cash";
    public static String ACCOUNT_TYPE_M_CASH = "m_cash";

    protected int accountIcon;
    protected String accountContextURI;
    protected String accountName;
    private final ArrayList<String> subAccoutList = new ArrayList<>();
    protected String account_id;
    private final String OTP_REQUEST_URL= "authenticate/otp/request";
    private final String OTP_VALIDATE_URL = "authenticate/otp/validate";
    private final String CUSTOMER_TOP_UP_URL = "customer/topup";
    private final String CUSTOMER_WITHDRAWAL_URL = "customer/withdrawal";
    private final String TRANSACTION_STATUS_URL = "transaction/status";
    private final String TRANSACTION_SUMMARY_URL = "transaction/summary";
    private final String BALANCE_CHECK_URL = "agent/balance";
    private final String BILL_PAYMENT_URL = "payment/bill";
    private final String UTILITY_PAYMENT_URL = "payment/utility";
    private final String INSTITUTIONAL_PAYMENT_URL = "payment/institutional";

    public String getAccount_id() {
        return account_id;
    }

    public String getAccountContextURI() {
        return accountContextURI;
    }

    public String getOTP_REQUEST_URL() {
        return accountContextURI + OTP_REQUEST_URL;
    }

    public String getOTP_VALIDATE_URL() {
        return accountContextURI + OTP_VALIDATE_URL;
    }

    public String getCUSTOMER_TOP_UP_URL() {
        return accountContextURI + CUSTOMER_TOP_UP_URL;
    }

    public String getCUSTOMER_WITHDRAWAL_URL() {
        return accountContextURI + CUSTOMER_WITHDRAWAL_URL;
    }

    public String getTRANSACTION_STATUS_URL() {
        return accountContextURI + TRANSACTION_STATUS_URL;
    }

    public String getTRANSACTION_SUMMARY_URL() {
        return accountContextURI + TRANSACTION_SUMMARY_URL;
    }

    public String getBALANCE_CHECK_URL() {
        return accountContextURI + BALANCE_CHECK_URL;
    }

    public String getBILL_PAYMENT_URL() {
        return accountContextURI + BILL_PAYMENT_URL;
    }

    public String getUTILITY_PAYMENT_URL() {
        return accountContextURI + UTILITY_PAYMENT_URL;
    }

    public String getINSTITUTIONAL_PAYMENT_URL() {
        return accountContextURI + INSTITUTIONAL_PAYMENT_URL;
    }

    public int getAccountIcon() {
        return accountIcon;
    }

    public ArrayList<String> getSubAccoutList() {
        return subAccoutList;
    }

    public String getAccountName() {
        return accountName;
    }

    public void addSubAccountToList(String subAccountId) {
        if (subAccountId != null) {
            this.subAccoutList.add(subAccountId);
        }
    }

    public abstract AccountDetail init();
}
