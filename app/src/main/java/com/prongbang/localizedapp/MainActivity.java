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

    private boolean USE_EXAMPLE_2 = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ex2
        if (USE_EXAMPLE_2) {
            // bind view
            bindSetting();
        }
    }

    @Override
    public void setContentView(@LayoutRes final int layoutResID) {
        super.setContentView(layoutResID);

        if (!USE_EXAMPLE_2) {

            // bind view
            bindSetting();

            Log.i(TAG, "setContentView: Reload");
        }
    }

    private void bindSetting() {
        ivSetting = findViewById(R.id.ivSetting);
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
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
