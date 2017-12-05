package com.prongbang.localizedapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.prongbang.localization.base.LocalizationAppCompatActivity;
import com.prongbang.localization.utils.LocaleHelper;
import com.prongbang.localizedapp.views.SettingActivity;

public class MainActivity extends LocalizationAppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ImageView ivSetting;

    private boolean USE_EXAMPLE_2 = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ex2
        if (USE_EXAMPLE_2) {
            // bind view
            ivSetting = findViewById(R.id.ivSetting);
            ivSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    // Ex1
    private String swip = LocaleHelper.THAI;

    @Override
    public void setContentView(@LayoutRes final int layoutResID) {
        super.setContentView(layoutResID);

        if (!USE_EXAMPLE_2) {

            // bind view
            ivSetting = findViewById(R.id.ivSetting);
            ivSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocaleHelper.changeLocale(getApplicationContext(), swip);
                    LocaleHelper.sendBroadcast(getApplicationContext(), LocaleHelper.changeLocale(getApplicationContext(), swip));
                    swip = swip.equals(LocaleHelper.THAI) ? LocaleHelper.ENGLISH : LocaleHelper.THAI;
                    Log.i(TAG, "onClick: " + swip);
                    setContentView(layoutResID);
                }
            });

            Log.i(TAG, "setContentView: Reload");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: ");

        // Ex1
        if (!USE_EXAMPLE_2) {
            setContentView(R.layout.activity_main);
        } else {
            // Ex2
            recreate();
        }
    }
}
