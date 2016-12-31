package com.intelligentz.cashpal.cashpal;

import com.andexert.library.RippleView;

/**
 * Created by lakshan on 12/30/16.
 */
public class Util {
    public static RippleView configureRippleView(RippleView rippleView, RippleView.OnRippleCompleteListener listener) {
        rippleView.setRippleDuration(100);
        rippleView.setRippleColor(R.color.colorAccent);
        rippleView.setRippleAlpha(300);
        rippleView.setOnRippleCompleteListener(listener);
        return rippleView;
    }

    public static String validateMobile (String mobile) {
        String validatedMobile = mobile;

        if (validatedMobile.charAt(0) == '0') {
            validatedMobile = "94"+validatedMobile.substring(1);
        }
        return validatedMobile;
    }

}
