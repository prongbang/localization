package com.prongbang.localization

import android.app.Application
import android.content.Context
import java.util.*

open class LocalizationApplication : Application(), LocalizationManager {

	override fun onCreate() {
		super.onCreate()
		LocalizeManager.initLocale(this)
	}

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(LocalizeManager.onAttach(base))
	}

	override fun setLocale(locale: Locale) {
		LocalizeManager.changeLocale(this, locale)
	}
}