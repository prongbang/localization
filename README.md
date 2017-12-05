# Localization

Supporting multiple languages on your android application.

## Download

```build.gradle```:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

...

dependencies {
    compile 'com.github.prongbang:localization:1.0.1'
}
```

## How to use

MainApplication.java
```java
public class MainApplication extends LocalizationApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO something...
    }
    
}
```

AndroidManifest.xml
```xml
<application
        android:name=".MainApplication"
        ...>
       ...
</application>
```

MainActivity.java
```java
public class MyActivity extends LocalizationAppCompatActivity {

     @Override
     public void onConfigurationChanged(Configuration newConfig) {
         super.onConfigurationChanged(newConfig);
         Log.i(TAG, "onConfigurationChanged: ");

         // Ex1
         setContentView(R.layout.my_layout);

         // Ex2
         recreate();
     }

     // Ex1
     private String swip = LocaleHelper.THAI;

     @Override
     public void setContentView(@LayoutRes final int layoutResID) {
         super.setContentView(layoutResID);
         mbtnSetting = findViewById(R.id.btnSetting);

         //bind view...

         mbtnSetting.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 LocaleHelper.changeLocale(getApplicationContext(), swip);
                 LocaleHelper.sendBroadcast(getApplicationContext(), LocaleHelper.changeLocale(getApplicationContext(), swip));
                 swip = swip.equals("th")? "en": "th";
                 setContentView(layoutResID);
             }
         });

     }

}

```
