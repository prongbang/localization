# Localization

Supporting multiple languages on your android application.

![screenshot gif](https://github.com/prongbang/Localization/blob/master/screenshots/screenshots.gif?raw=true)

## Download

```build.gradle```:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Support Library

```gradle
implementation 'com.github.prongbang:localization:1.0.1'
```

- AndroidX

```gradle
implementation 'com.github.prongbang:localization:2.0.0'
```

## How to use

MainApplication.java
```java
public class MainApplication extends LocalizationApplication {

    @Override
    public void onCreate() {
        LocaleHelper.changeLocale(getApplicationContext(), LocaleHelper.THAI);
        super.onCreate();
    }
    
}
```

AndroidManifest.xml
```xml
<application
        android:name=".MainApplication"
        ...>
       <activity
            android:name=".MainActivity"
            android:configChanges="locale">
           ...
        </activity>
</application>
```

Example 1: in Activity
```java
public class MainActivity extends LocalizationAppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     @Override
     public void onConfigurationChanged(Configuration newConfig) {
         super.onConfigurationChanged(newConfig);
         Log.i(TAG, "onConfigurationChanged: ");

         setContentView(R.layout.activity_main);
     }

     @Override
     public void setContentView(@LayoutRes final int layoutResID) {
         super.setContentView(layoutResID);

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

```


Example 2: in Activity
```java
public class MainActivity extends LocalizationAppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         super.setContentView(R.layout.activity_main);

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

     @Override
     public void onConfigurationChanged(Configuration newConfig) {
         super.onConfigurationChanged(newConfig);
         Log.i(TAG, "onConfigurationChanged: ");

         recreate();
     }

}

```

SettingActivity
```java
public class SettingActivity extends LocalizationAppCompatActivity {

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
```
