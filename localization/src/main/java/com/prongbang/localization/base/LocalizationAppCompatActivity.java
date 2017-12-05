package com.prongbang.localization.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.prongbang.localization.utils.ConstantUtil;
import com.prongbang.localization.utils.LocaleHelper;

/**
 * Created by prongbang on 10/20/2017 AD.
 *
 * How to use:
 *
 * public class MyActivity extends LocalizationAppCompatActivity {
 *
 *      @Override
 *      public void onConfigurationChanged(Configuration newConfig) {
 *          super.onConfigurationChanged(newConfig);
 *          Log.i(TAG, "onConfigurationChanged: ");
 *
 *          // Ex1
 *          setContentView(R.layout.my_layout);
 *
 *          // Ex2
 *          recreate();
 *      }
 *
 *      // Ex1
 *      private String swip = LocaleHelper.THAI;
 *
 *      @Override
 *      public void setContentView(@LayoutRes final int layoutResID) {
 *          super.setContentView(layoutResID);
 *          mbtnSetting = findViewById(R.id.btnSetting);
 *
 *          bind view...
 *
 *          mbtnSetting.setOnClickListener(new View.OnClickListener() {
 *
 *              @Override
 *              public void onClick(View v) {
 *                  LocaleHelper.changeLocale(getApplicationContext(), swip);
 *                  LocaleHelper.sendBroadcast(getApplicationContext(), LocaleHelper.changeLocale(getApplicationContext(), swip));
 *                  swip = swip.equals("th")? "en": "th";
 *                  setContentView(layoutResID);
 *
 *                  Log.i(TAG, "onClick: " + swip);
 *                  Log.i(TAG,"setContentView: Reload");
 *              }
 *          });
 *
 *      }
 *
 * }
 *
 */

public class LocalizationAppCompatActivity extends AppCompatActivity {

    private final static String TAG = LocalizationAppCompatActivity.class.getSimpleName();

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

    /**
     * LocaleHelper.sendBroadcast(getApplicationContext(), LocaleHelper.changeLocale(getApplicationContext(), "th"));
     */
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
