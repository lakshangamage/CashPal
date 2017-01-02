package com.intelligentz.cashpal.cashpal.model;

/**
 * Created by lakshan on 12/26/16.
 */
public class Strings {
    protected static String CUSTOMER_TOP_UP_TITLE;
    protected static String AGENT_PIN_TEXT_HINT;
    protected static String MOBILE_TEXT_HINT;
    protected static String AMOUNT_TEXT_HINT;
    protected static String TOP_UP_BUTTON_TEXT;
    protected static String SUBMIT_TRANSACTION_WARNING_DIALOG_TITLE;
    protected static String SUBMIT_TRANSACTION_WARNING_DIALOG_BODY;
    protected static String SUBMIT_TRANSACTION_PROCESSING_DIALOG_TITLE;
    protected static String SUBMIT_TRANSACTION_PROCESSING_DIALOG_BODY;
    protected static String NO_TEXT;
    protected static String YES_TEXT;
    protected static String CANCEL_TEXT;
    protected static String OK_TEXT;
    protected static String SUCCESS_TEXT;
    protected static String FAILED_TEXT;
    protected static String INVALID_TEXT;
    protected static String SUBMIT_TRANSACTION_SUCCESS_DIALOG_BODY;
    protected static String SUBMIT_TRANSACTION_FAILURE_DIALOG_BODY;
    protected static String LOG_IN_SPINNER_PROMPT;
    protected static String LOG_IN_PROCESSING_TITLE;
    protected static String LOG_IN_TEXT;
    protected static String VERIFY_TEXT;
    protected static String VERIFY_PROCESSING_TITLE;
    protected static String PIN_VERIFICATION_FALIURE_DIALOG_BODY;
    protected static String PROCEED_TEXT;
    protected static String OTP_VALIDATION_GUIDE_MESSAGE;
    protected static String CUSTOMER_CASH_WITHDRAWAL_TITLE;
    protected static String BALANCE_CHECK_TITLE;
    protected static String SET_TEXT;
    protected static String LANGUAGE_CHANGE_MESSAGE;
    protected static String ALREADY_LOGGED_IN_MESSAGE;
    protected static String NO_ACCOUNT_REGISTERED_MESSAGE;
    protected static String PAYMENT_TITLE;
    protected static String BILL_PAYMENT_BUTTON_TEXT;
    protected static String UTILITY_PAYMENT_BUTTON_TEXT;
    protected static String INSTITUTIONAL_PAYMENT_BUTTON_TEXT;
    protected static String RECEIVE_PAYMENT_BUTTON_TEXT;
    protected static String BILL_TYPE_TEXT;
    protected static String ACCOUNT_NO_TEXT;
    protected static String PAY_TEXT;
    protected static String UTILITY_TYPE_TEXT;
    protected static String INSTITUTION_TEXT;
    protected static String RECEIVE_TEXT;


    static {
        English.setLanguage();
    }
    public static String getCustomerTopUpTitle() {
        return CUSTOMER_TOP_UP_TITLE;
    }

    public static String getTopUpButtonText() {
        return TOP_UP_BUTTON_TEXT;
    }

    public static String getAgentPinTextHint() {
        return AGENT_PIN_TEXT_HINT;
    }

    public static String getMobileTextHint() {
        return MOBILE_TEXT_HINT;
    }

    public static String getAmountTextHint() {
        return AMOUNT_TEXT_HINT;
    }

    public static String getLogInSpinnerPrompt() {
        return LOG_IN_SPINNER_PROMPT;
    }

    public static String getSubmitTransactionWarningDialogTitle() {
        return SUBMIT_TRANSACTION_WARNING_DIALOG_TITLE;
    }

    public static String getSubmitTransactionWarningDialogBody() {
        return SUBMIT_TRANSACTION_WARNING_DIALOG_BODY;
    }

    public static String getSubmitTransactionProcessingDialogTitle() {
        return SUBMIT_TRANSACTION_PROCESSING_DIALOG_TITLE;
    }

    public static String getSubmitTransactionProcessingDialogBody() {
        return SUBMIT_TRANSACTION_PROCESSING_DIALOG_BODY;
    }

    public static String getNoText() {
        return NO_TEXT;
    }

    public static String getYesText() {
        return YES_TEXT;
    }

    public static String getCancelText() {
        return CANCEL_TEXT;
    }

    public static String getOkText() {
        return OK_TEXT;
    }

    public static String getSuccessText() {
        return SUCCESS_TEXT;
    }

    public static String getFailedText() {
        return FAILED_TEXT;
    }

    public static String getSubmitTransactionSuccessDialogBody() {
        return SUBMIT_TRANSACTION_SUCCESS_DIALOG_BODY;
    }

    public static String getSubmitTransactionFailureDialogBody() {
        return SUBMIT_TRANSACTION_FAILURE_DIALOG_BODY;
    }

    public static String getLogInText() {
        return LOG_IN_TEXT;
    }

    public static String getInvalidText() {
        return INVALID_TEXT;
    }

    public static String getLogInProcessingTitle() {
        return LOG_IN_PROCESSING_TITLE;
    }

    public static String getVerifyProcessingTitle() {
        return VERIFY_PROCESSING_TITLE;
    }

    public static String getPinVerificationFaliureDialogBody() {
        return PIN_VERIFICATION_FALIURE_DIALOG_BODY;
    }

    public static String getProceedText() {
        return PROCEED_TEXT;
    }

    public static String getVerifyText() {
        return VERIFY_TEXT;
    }

    public static String getOtpValidationGuideMessage() {
        return OTP_VALIDATION_GUIDE_MESSAGE;
    }

    public static String getCustomerCashWithdrawalTitle() {
        return CUSTOMER_CASH_WITHDRAWAL_TITLE;
    }

    public static String getBalanceCheckTitle() {
        return BALANCE_CHECK_TITLE;
    }

    public static String getSetText() {
        return SET_TEXT;
    }

    public static String getLanguageChangeMessage() {
        return LANGUAGE_CHANGE_MESSAGE;
    }

    public static String getAlreadyLoggedInMessage() {
        return ALREADY_LOGGED_IN_MESSAGE;
    }

    public static String getNoAccountRegisteredMessage() {
        return NO_ACCOUNT_REGISTERED_MESSAGE;
    }

    public static String getBillPaymentButtonText() {
        return BILL_PAYMENT_BUTTON_TEXT;
    }

    public static String getInstitutionalPaymentButtonText() {
        return INSTITUTIONAL_PAYMENT_BUTTON_TEXT;
    }

    public static String getPaymentTitle() {
        return PAYMENT_TITLE;
    }

    public static String getReceivePaymentButtonText() {
        return RECEIVE_PAYMENT_BUTTON_TEXT;
    }

    public static String getUtilityPaymentButtonText() {
        return UTILITY_PAYMENT_BUTTON_TEXT;
    }

    public static String getBillTypeText() {
        return BILL_TYPE_TEXT;
    }

    public static String getAccountNoText() {
        return ACCOUNT_NO_TEXT;
    }

    public static String getPayText() {
        return PAY_TEXT;
    }

    public static String getUtilityTypeText() {
        return UTILITY_TYPE_TEXT;
    }

    public static String getInstitutionText() {
        return INSTITUTION_TEXT;
    }

    public static String getReceiveText() {
        return RECEIVE_TEXT;
    }

    public static class English {
        public static void setLanguage() {
            Strings.CUSTOMER_TOP_UP_TITLE = "Customer Top Up";
            Strings.AGENT_PIN_TEXT_HINT = "Pin";
            Strings.MOBILE_TEXT_HINT = "Mobile";
            Strings.AMOUNT_TEXT_HINT = "Amount";
            Strings.TOP_UP_BUTTON_TEXT = "Top Up";
            Strings.SUBMIT_TRANSACTION_WARNING_DIALOG_TITLE = "Submit Transaction?";
            Strings.SUBMIT_TRANSACTION_WARNING_DIALOG_BODY = "Customer: %s \nAmount: %s";
            Strings.NO_TEXT = "No";
            Strings.YES_TEXT = "Yes";
            Strings.CANCEL_TEXT = "Cancel";
            Strings.SUBMIT_TRANSACTION_PROCESSING_DIALOG_BODY = "This might take few seconds.";
            Strings.SUBMIT_TRANSACTION_PROCESSING_DIALOG_TITLE = "Processing Request...";
            Strings.OK_TEXT = "OK";
            Strings.SUCCESS_TEXT = "Success!";
            Strings.FAILED_TEXT = "Failed!";
            Strings.INVALID_TEXT = "Invalid";
            Strings.SUBMIT_TRANSACTION_SUCCESS_DIALOG_BODY = "Your transaction request sent successfully.";
            Strings.SUBMIT_TRANSACTION_FAILURE_DIALOG_BODY = "Something went wrong. Please try again.";
            Strings.LOG_IN_SPINNER_PROMPT = "Account Type";
            Strings.LOG_IN_PROCESSING_TITLE = "Loging In...";
            Strings.LOG_IN_TEXT = "Log In";
            Strings.VERIFY_TEXT = "Verify";
            Strings.VERIFY_PROCESSING_TITLE = "Verifying...";
            Strings.PIN_VERIFICATION_FALIURE_DIALOG_BODY = "Some thing went wrong. Please check your pin and try again";
            Strings.PROCEED_TEXT = "Proceed";
            Strings.OTP_VALIDATION_GUIDE_MESSAGE = "Enter the pass code which you will receive as a SMS shortly.";
            Strings.CUSTOMER_CASH_WITHDRAWAL_TITLE = "Customer Withdrawal";
            Strings.BALANCE_CHECK_TITLE = "Balance Check";
            Strings.SET_TEXT = "Set";
            Strings.LANGUAGE_CHANGE_MESSAGE = "Select your preferred Language.";
            Strings.ALREADY_LOGGED_IN_MESSAGE = "You have already added this account";
            Strings.NO_ACCOUNT_REGISTERED_MESSAGE = "You have no %s account added.";
            Strings.PAYMENT_TITLE = "Payment";
            Strings.BILL_PAYMENT_BUTTON_TEXT = "Bill Payment";
            Strings.UTILITY_PAYMENT_BUTTON_TEXT = "Utility Payment";
            Strings.INSTITUTIONAL_PAYMENT_BUTTON_TEXT = "Institutional Payment";
            Strings.RECEIVE_PAYMENT_BUTTON_TEXT = "Receive Payment";
            Strings.BILL_TYPE_TEXT = "Type of Bill";
            Strings.ACCOUNT_NO_TEXT = "Account No.";
            Strings.PAY_TEXT = "Pay";
            Strings.UTILITY_TYPE_TEXT = "Type of Utility";
            Strings.INSTITUTION_TEXT = "Institution";
            Strings.RECEIVE_TEXT = "Receive";
        }

    }

    public static class Sinhala {
        public static void setLanguage() {
            Strings.CUSTOMER_TOP_UP_TITLE = "පාරිභෝගික ගිණුමට මුදල් යැවීම";
            Strings.AGENT_PIN_TEXT_HINT = "මුරපදය";
            Strings.MOBILE_TEXT_HINT = "දුරකතන අංකය";
            Strings.AMOUNT_TEXT_HINT = "මුදල";
            Strings.TOP_UP_BUTTON_TEXT = "මුදල් යවන්න";
            Strings.SUBMIT_TRANSACTION_WARNING_DIALOG_TITLE = "තහවුරු කරන්න?";
            Strings.SUBMIT_TRANSACTION_WARNING_DIALOG_BODY = "පාරිභෝගිකයා: %s \nමුදල: %s";
            Strings.NO_TEXT = "නැත";
            Strings.YES_TEXT = "ඔව්";
            Strings.CANCEL_TEXT = "අවලංගු කරන්න";
            Strings.SUBMIT_TRANSACTION_PROCESSING_DIALOG_BODY = "මදක් රැඳී සිටින්න.";
            Strings.SUBMIT_TRANSACTION_PROCESSING_DIALOG_TITLE = "ඔබේ ඉල්ලීම ඉටු කරමින්...";
            Strings.OK_TEXT = "හරි";
            Strings.SUCCESS_TEXT = "සාර්ථකයි!";
            Strings.FAILED_TEXT = "අසාර්ථකයි";
            Strings.INVALID_TEXT = "වැරදියි";
            Strings.SUBMIT_TRANSACTION_SUCCESS_DIALOG_BODY = "ඔබේ ඉල්ලීම සාර්ථකව යොමු කෙරින ලදී.";
            Strings.SUBMIT_TRANSACTION_FAILURE_DIALOG_BODY = "කරුණාකර නැවත උත්සාහ කරන්න.";
            Strings.LOG_IN_SPINNER_PROMPT = "මුදල් වර්ගය";
            Strings.LOG_IN_PROCESSING_TITLE = "සම්බන්ධ කරමින්...";
            Strings.LOG_IN_TEXT = "ඇතුල් වන්න";
            Strings.VERIFY_TEXT = "තහවුරු කරන්න";
            Strings.VERIFY_PROCESSING_TITLE = "තහවුරු කරමින්...";
            Strings.PIN_VERIFICATION_FALIURE_DIALOG_BODY = "කරුණාකර ඔබේ කේතය නැවත පරීක්ෂා කරන්න.";
            Strings.PROCEED_TEXT = "ඉදිරියට ";
            Strings.OTP_VALIDATION_GUIDE_MESSAGE = "ඔබට SMS පණිවුඩයක් හරහා එවන ලද රහස්ය කේතය ඇතුළත් කරන්න";
            Strings.CUSTOMER_CASH_WITHDRAWAL_TITLE = "පාරිභෝගික ගිණුමෙන් මුදල් ගැනීම";
            Strings.BALANCE_CHECK_TITLE = "ගිණුම් ශේෂය විමසීම";
            Strings.SET_TEXT = "සක්\u200Dරිය කරන්න";
            Strings.LANGUAGE_CHANGE_MESSAGE = "ඔබ කැමති භාෂාවක් තෝරන්න";
            Strings.ALREADY_LOGGED_IN_MESSAGE = "ඔබ දැනටමත් මෙම ගිණුම ක්\u200Dරියාත්මක කර ඇත.";
            Strings.NO_ACCOUNT_REGISTERED_MESSAGE = "ඔබට ලියාපදිංචි කළ %s ගිණුම් කිසිවක් නොමැත.";
            Strings.PAYMENT_TITLE = "ගෙවීම්";
            Strings.BILL_PAYMENT_BUTTON_TEXT = "බිල්පත් ගෙවීම්";
            Strings.UTILITY_PAYMENT_BUTTON_TEXT = "උපයෝගීතා ගෙවීම්";
            Strings.INSTITUTIONAL_PAYMENT_BUTTON_TEXT = "ආයතනික ගෙවීම්";
            Strings.RECEIVE_PAYMENT_BUTTON_TEXT = "පාරිභෝගික අය කිරීම්";
            Strings.BILL_TYPE_TEXT = "බිල්පත් වර්ගය";
            Strings.ACCOUNT_NO_TEXT = "ගිණුම් අංකය";
            Strings.PAY_TEXT = "ගෙවන්න";
            Strings.UTILITY_TYPE_TEXT = "උපයෝගීතා වර්ගය";
            Strings.INSTITUTION_TEXT = "ආයතනය";
            Strings.RECEIVE_TEXT = "අය කරගන්න";
        }
    }



}
