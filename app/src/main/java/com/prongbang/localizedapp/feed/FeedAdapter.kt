package com.prongbang.localizedapp.feed

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class FeedAdapter : ListAdapter<Feed, FeedViewHolder>(DIFF_COMPARATOR) {

	var onItemClick: (Feed) -> Unit = {}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
		return FeedViewHolder.newInstance(parent)
	}

	override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
		holder.bind(getItem(position), onItemClick)
	}

	companion object {
		private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Feed>() {
			override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
				return oldItem == newItem
			}

			override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
				return oldItem.id == newItem.id
			}
		}
	}

}