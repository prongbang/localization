package com.prongbang.localizedapp

import android.os.Bundle
import com.prongbang.localization.LocalizationAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LocalizationAppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		ivSetting.setOnClickListener {
			SettingActivity.navigate(this)
		}
	}

}