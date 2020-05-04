package com.prongbang.localization

import android.content.Context
import org.intellij.lang.annotations.Language


object LocaleManager {

//	fun wrap(context: Context): Context {
//		val configPreference = LanguagePreference(
//				UChooseSharePreferenceUtility(context))
//		val lang = getCurrentLanguage(configPreference)
//		return setLocale(context, lang)
//	}
//
//	private fun getCurrentLanguage(configPreference: LanguagePreference): String {
//		return when (configPreference.getLocale().toUpperCase()) {
//			Language.EN.name -> Language.EN.name.toLowerCase()
//			Language.TH.name -> Language.TH.name.toLowerCase()
//			else -> {
//				Language.EN.name.toLowerCase()
//			}
//		}
//	}
//
//	private fun setLocale(context: Context, language: String): Context {
//		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//			updateResources(context, language)
//		} else {
//			updateResourcesLegacy(context, language)
//		}
//	}
//
//	@TargetApi(Build.VERSION_CODES.N)
//	private fun updateResources(context: Context, language: String): Context {
//		val locale = Locale(language)
//		Locale.setDefault(locale)
//
//		val configuration = context.resources.configuration
//		configuration.setLocale(locale)
//		configuration.setLayoutDirection(locale)
//
//		return context.createConfigurationContext(configuration)
//	}
//
//	@SuppressLint("ObsoleteSdkInt")
//	@Suppress("DEPRECATION")
//	private fun updateResourcesLegacy(context: Context, language: String): Context {
//		val locale = Locale(language)
//		Locale.setDefault(locale)
//
//		val resources = context.resources
//
//		val configuration = resources.configuration
//		configuration.locale = locale
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//			configuration.setLayoutDirection(locale)
//		}
//
//		resources.updateConfiguration(configuration, resources.displayMetrics)
//
//		return context
//	}

}