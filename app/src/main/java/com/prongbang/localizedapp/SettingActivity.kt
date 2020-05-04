package com.prongbang.localizedapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.prongbang.localization.ENGLISH
import com.prongbang.localization.LocalizationAppCompatActivity
import com.prongbang.localization.Localize
import com.prongbang.localization.THAI
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : LocalizationAppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_setting)

		tvThai.setOnClickListener { setLocale(Localize.THAI) }
		tvEnglish.setOnClickListener { setLocale(Localize.ENGLISH) }
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		openPrepareLocalize() // used only in setting activity
		super.onConfigurationChanged(newConfig)
	}

	companion object {
		fun navigate(context: Context) {
			context.startActivity(Intent(context, SettingActivity::class.java))
		}
	}
}
