package com.prongbang.localization

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

class PrepareActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_prepare)
		setUpStatusBarTransparent()
	}

	private fun setUpStatusBarTransparent() {
		findViewById<RelativeLayout>(R.id.rootView)?.let { rootView ->
			ViewCompat.setOnApplyWindowInsetsListener(rootView) { _, insets ->
				insets.consumeSystemWindowInsets()
			}
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window?.apply {
				clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
				addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
				} else {
					decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				}
				statusBarColor = Color.TRANSPARENT
			}
		}
	}

	override fun onResume() {
		super.onResume()
		Handler().postDelayed({
			finish()
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
		}, LocalizeConstant.FINISH_DELAY)
	}

	companion object {
		fun navigate(activity: Activity) {
			val intent = Intent(activity, PrepareActivity::class.java)
			activity.apply {
				startActivity(intent)
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
			}
		}
	}
}
