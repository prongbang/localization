package com.prongbang.localization.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.prongbang.localization.utils.ConstantUtil;
import com.prongbang.localization.utils.LocaleHelper;

/**
 * Created by prongbang on 10/21/2017 AD.
 */

public class BaseAppCompatPreferenceActivity extends AppCompatPreferenceActivity {

    private static final String TAG = BaseAppCompatPreferenceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver(mBroadcastReceiver, new IntentFilter(ConstantUtil.ON_LOCALE_CHANGED_ACTION));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, ConstantUtil.LOCALE_DEFAULT));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: " + intent.getAction());
            Configuration configuration = LocaleHelper.getConfigurationChanged(intent);
            if (configuration != null) {
                onConfigurationChanged(configuration);
            }
        }
    };
}
