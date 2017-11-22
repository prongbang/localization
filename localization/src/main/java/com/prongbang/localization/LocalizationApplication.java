package com.prongbang.localization;

import android.app.Application;
import android.content.Context;

import com.prongbang.localization.utils.ConstantUtil;
import com.prongbang.localization.utils.LocaleHelper;

/**
 * Created by prongbang on 11/22/2017 AD.
 */

public class LocalizationApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LocaleHelper.setLocale(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, ConstantUtil.LOCALE_DEFAULT));
    }

}
