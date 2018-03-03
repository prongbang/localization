package com.prongbang.localizedapp;

import com.prongbang.localization.LocalizationApplication;
import com.prongbang.localization.utils.LocaleHelper;

/**
 * Created by mdev on 10/21/2017 AD.
 */

public class MainApplication extends LocalizationApplication {

    @Override
    public void onCreate() {
        LocaleHelper.changeLocale(getApplicationContext(), LocaleHelper.THAI);
        super.onCreate();
    }
}
