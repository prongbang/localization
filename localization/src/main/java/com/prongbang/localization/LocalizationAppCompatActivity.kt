package com.prongbang.localization

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.prongbang.localization.LocalizeManager.getConfigurationChanged
import java.util.*

open class LocalizationAppCompatActivity : AppCompatActivity(), LocalizationManager {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		registerReceiver(broadcastReceiver, IntentFilter(
				LocalizeConstant.ON_LOCALE_CHANGED_ACTION))
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
		val configuration = LocalizeManager.changeLocale(this, locale)
		LocalizeManager.sendBroadcast(this, configuration)
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		super.onConfigurationChanged(newConfig)
		Handler().postDelayed({ recreate() }, LocalizeConstant.PREPARE_DELAY)
	}

	open fun openPrepareLocalize() {
		PrepareActivity.navigate(this)
	}
}