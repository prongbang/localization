package com.prongbang.localization

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.preference.PreferenceManager
import java.util.*

object LocalizeManager {

	private const val SELECTED_LANGUAGE = "Locale.Manager.Selected.Language"

	fun onAttach(context: Context): Context {
		val lang = getPersistedData(context, Locale.getDefault().language)
		return setLocale(context, lang)
	}

	fun onAttach(context: Context, defaultLanguage: String): Context {
		val lang = getPersistedData(context, defaultLanguage)
		return setLocale(context, lang)
	}

	fun getLanguage(context: Context): String {
		return getPersistedData(context, Locale.getDefault().language)
	}

	fun setLocale(context: Context, language: String): Context {
		persist(context, language)
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			updateResources(context, language)
		} else updateResourcesLegacy(context, language)
	}

	private fun getPersistedData(context: Context, defaultLanguage: String): String {
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		return preferences.getString(SELECTED_LANGUAGE, defaultLanguage) ?: defaultLanguage
	}

	private fun persist(context: Context, language: String) {
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		val editor = preferences.edit()
		editor.putString(SELECTED_LANGUAGE, language)
		editor.commit()
	}

	@TargetApi(Build.VERSION_CODES.N)
	private fun updateResources(context: Context, language: String): Context {
		val locale = Locale(language)
		Locale.setDefault(locale)
		val configuration = context.resources.configuration
		configuration.setLocale(locale)
		configuration.setLayoutDirection(locale)
		return context.createConfigurationContext(configuration)
	}

	@Suppress("DEPRECATION")
	private fun updateResourcesLegacy(context: Context, language: String): Context {
		val locale = Locale(language)
		Locale.setDefault(locale)
		val resources: Resources = context.resources
		val configuration: Configuration = resources.configuration
		configuration.locale = locale
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			configuration.setLayoutDirection(locale)
		}
		resources.updateConfiguration(configuration, resources.displayMetrics)
		return context
	}

	fun getConfigurationChanged(intent: Intent?): Configuration? {
		if (intent?.action?.isEmpty() == false) {
			if (intent.action == LocalizeConstant.ON_LOCALE_CHANGED_ACTION) {
				val bundle = intent.extras
				return if (bundle != null) {
					bundle.getParcelable<Parcelable>(
							LocalizeConstant.CONFIGURATION_KEY) as? Configuration
				} else null
			}
		}
		return null
	}

	fun sendBroadcast(context: Context, configuration: Configuration?) {
		val intent = Intent(LocalizeConstant.ON_LOCALE_CHANGED_ACTION).apply {
			val bundle = Bundle().apply {
				putParcelable(LocalizeConstant.CONFIGURATION_KEY, configuration)
			}
			putExtras(bundle)
		}
		context.sendBroadcast(intent)
	}

	fun changeLocale(context: Context, locale: Locale): Configuration? {
		setLanguage(context, locale)
		return setLocale(context)
	}

	fun setLanguage(context: Context, lang: Locale) {
		val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
		val editor = sharedPreferences.edit()
		editor.putString(LocalizeConstant.PREF_LANGUAGE_KEY, lang.language)
		editor.commit()
	}

	fun getLocale(context: Context): Locale {
		val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
		val lang = sharedPreferences.getString(LocalizeConstant.PREF_LANGUAGE_KEY,
				Localize.DEFAULT.language) ?: Localize.DEFAULT.language
		return Locale(lang)
	}

	@Suppress("DEPRECATION")
	fun setLocale(context: Context): Configuration? {
		val resources: Resources = context.resources
		val configuration: Configuration = resources.configuration
		val locale: Locale = getLocale(context)
		if (configuration.locale != locale) {
			configuration.setLocale(locale)
			setLanguage(context, locale)
			resources.updateConfiguration(configuration, resources.displayMetrics)
			return configuration
		}
		return null
	}

	fun initLocale(context: Context) {
		val locale = getLocale(context)
		setLocale(context, locale.language)
	}
}
