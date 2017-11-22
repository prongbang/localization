package com.prongbang.localizedapp.views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.prongbang.localization.base.BaseAppCompatActivity;
import com.prongbang.localization.utils.LocaleHelper;
import com.prongbang.localizedapp.R;

public class SettingActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViewById(R.id.tvThai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.sendBroadcast(getApplicationContext(), LocaleHelper.changeLocale(getApplicationContext(), LocaleHelper.THAI));
                finishDelay(1000);
            }
        });

        findViewById(R.id.tvEnglish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.sendBroadcast(getApplicationContext(), LocaleHelper.changeLocale(getApplicationContext(), LocaleHelper.ENGLISH));
                finishDelay(1000);
            }
        });
    }

    private void finishDelay(long delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, delayMillis);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        recreate();
    }
}
