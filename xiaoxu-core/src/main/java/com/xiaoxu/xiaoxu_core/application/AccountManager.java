package com.xiaoxu.xiaoxu_core.application;

import com.xiaoxu.xiaoxu_core.util.storage.XiaoXuPreference;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 * for user account manager
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存登录状态，登录后调用
    public static void setSignInState(boolean state){
        XiaoXuPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean  isSignIn(){
        return XiaoXuPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNonSignIn();
        }
    }
}
