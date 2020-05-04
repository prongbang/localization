package com.prongbang.localizedapp.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prongbang.localizedapp.databinding.ItemFeedBinding

class FeedViewHolder(
		private val binding: ItemFeedBinding
) : RecyclerView.ViewHolder(binding.root) {

	fun bind(item: Feed, onItemClick: (Feed) -> Unit) {
		itemView.setOnClickListener {
			onItemClick.invoke(item)
		}
		binding.apply {
			Glide.with(binding.root.context)
					.load(item.imageUrl)
					.into(feedImage)
			titleText.text = item.title
			descriptionText.text = item.description
		}
	}

	companion object {
		fun newInstance(parent: ViewGroup): FeedViewHolder {
			return FeedViewHolder(
					ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
			)
		}
	}
}