package com.prongbang.localizedapp.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.prongbang.localizedapp.databinding.ActivityFeedBinding
import kotlinx.android.synthetic.main.item_feed.*

class FeedActivity : AppCompatActivity() {

	private val binding by lazy { ActivityFeedBinding.inflate(layoutInflater) }
	private val feed by lazy { intent.getParcelableExtra<Feed>(Feed::class.java.simpleName) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		initView()
	}

	private fun initView() {
		binding.apply {
			Glide.with(this@FeedActivity)
					.load(feed.imageUrl)
					.into(feedImage)
			feedView.titleText.text = feed.title
			feedView.descriptionText.text = feed.description
		}
	}

	companion object {
		fun navigate(context: Context, feed: Feed) {
			val intent = Intent(context, FeedActivity::class.java).apply {
				putExtras(Bundle().apply {
					putParcelable(Feed::class.java.simpleName, feed)
				})
			}
			context.startActivity(intent)
		}
	}
}
