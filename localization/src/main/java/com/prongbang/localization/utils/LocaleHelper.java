package com.prongbang.localization.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * This class is used to change your application locale and persist this change for the next time
 * that your app is going to be used.
 * <p/>
 * You can also change the locale of your application on the fly by using the setLocale method.
 * <p>
 * Source Code https://gist.github.com/gunhansancar/45648176dc47d50b1940
 * <p/>
 * Created by gunhansancar on 07/10/15.
 * <p>
 * Modify by prongbang on 10/21/2017 AD.
 */

public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public static final String THAI = "th";
    public static final String ENGLISH = "en";

    public static Context onAttach(Context context) {
        String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        return setLocale(context, lang);
    }

    public static Context onAttach(Context context, String defaultLanguage) {
        String lang = getPersistedData(context, defaultLanguage);
        return setLocale(context, lang);
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }

    public static Context setLocale(Context context, String language) {
        persist(context, language);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }

        return updateResourcesLegacy(context, language);
    }

    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    public static Configuration getConfigurationChanged(Intent intent) {
        if (intent != null) {
            if (null != intent.getAction() && !intent.getAction().isEmpty()) {
                if (intent.getAction().equals(ConstantUtil.ON_LOCALE_CHANGED_ACTION)) {
                    Bundle bundle = intent.getExtras();
                    if (bundle != null)
                        return (Configuration) bundle.getParcelable(ConstantUtil.CONFIGURATION_KEY);
                    return null;
                }
            }
        }
        return null;
    }

    public static void sendBroadcast(Context context, Configuration configuration) {
        Intent intent = new Intent(ConstantUtil.ON_LOCALE_CHANGED_ACTION);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantUtil.CONFIGURATION_KEY, configuration);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }

    public static Configuration changeLocale(Context context, String locale) {
        LocaleHelper.setLanguage(context, locale);
        return LocaleHelper.setLocale(context);
    }

    public static void setLanguage(Context context, String lang) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantUtil.PREF_LANGUAGE_KEY, lang);
        editor.commit();
    }

    public static Locale getLocale(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = sharedPreferences.getString(ConstantUtil.PREF_LANGUAGE_KEY, ConstantUtil.LOCALE_DEFAULT);
        return new Locale(lang);
    }

    public static Configuration setLocale(Context context) {
        final Resources resources = context.getResources();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale = LocaleHelper.getLocale(context);
        if (!configuration.locale.equals(locale)) {
            configuration.setLocale(locale);
            LocaleHelper.setLanguage(context, locale.getLanguage());
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            return configuration;
        }
        return null;
    }

    /**
     * activity.recreate() to restart currently loaded activity. Then the activity will reload the
     * resources with the correct locale.
     *
     * @param activity
     */
    public static void recreate(Activity activity) {
        activity.recreate();
    }

}
