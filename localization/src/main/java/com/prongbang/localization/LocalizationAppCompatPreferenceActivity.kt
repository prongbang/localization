package com.prongbang.localization

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import com.prongbang.localization.LocalizeManager.getConfigurationChanged
import java.util.*

@Deprecated("Deprecated Use the AndroidX Preference Library for consistent behavior across all devices")
class LocalizationAppCompatPreferenceActivity : AppCompatPreferenceActivity(), LocalizationManager {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		registerReceiver(broadcastReceiver, IntentFilter(LocalizeConstant.ON_LOCALE_CHANGED_ACTION))
	}

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(LocalizeManager.onAttach(base))
	}

	public override fun onDestroy() {
		broadcastReceiver?.let { unregisterReceiver(it) }
		super.onDestroy()
	}

	private val broadcastReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
		override fun onReceive(context: Context, intent: Intent) {
			val configuration = getConfigurationChanged(intent)
			configuration?.let { onConfigurationChanged(it) }
		}
	}

	override fun setLocale(locale: Locale) {
		LocalizeManager.sendBroadcast(this, LocalizeManager.changeLocale(this, locale))
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		super.onConfigurationChanged(newConfig)
		Handler().postDelayed({ recreate() }, LocalizeConstant.PREPARE_DELAY)
	}

	fun openPrepareLocalize() {
		PrepareActivity.navigate(this)
	}
}
