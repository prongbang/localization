package com.prongbang.localizedapp

import android.os.Bundle
import com.prongbang.localization.ENGLISH
import com.prongbang.localization.LocalizationAppCompatActivity
import com.prongbang.localization.Localize
import com.prongbang.localization.LocalizeManager
import com.prongbang.localization.THAI
import com.prongbang.localizedapp.feed.Feed
import com.prongbang.localizedapp.feed.FeedActivity
import com.prongbang.localizedapp.feed.FeedAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LocalizationAppCompatActivity() {

	private val feedAdapter by lazy { FeedAdapter() }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initView()
		initListener()
	}

	override fun onResume() {
		super.onResume()
		loadFeed()
	}

	private fun initView() {
		feedRecyclerView.apply {
			adapter = feedAdapter
		}
		feedAdapter.apply {
			onItemClick = {
				FeedActivity.navigate(this@MainActivity, it)
			}
		}
	}

	private fun initListener() {
		ivSetting.setOnClickListener {
			SettingActivity.navigate(this)
		}
	}

	private fun loadFeed() {
		val locale = LocalizeManager.getLocale(this)
		when (locale.language) {
			Localize.THAI.language -> {
				feedAdapter.submitList(Feed.asListTH())
			}
			Localize.ENGLISH.language -> {
				feedAdapter.submitList(Feed.asListEN())
			}
		}
	}

}