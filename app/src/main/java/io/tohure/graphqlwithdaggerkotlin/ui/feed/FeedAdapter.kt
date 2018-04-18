package io.tohure.graphqlwithdaggerkotlin.ui.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.tohure.graphqlwithdaggerkotlin.FeedQuery
import io.tohure.graphqlwithdaggerkotlin.R
import kotlinx.android.synthetic.main.item_feed.view.*
import java.util.*

/**
 * Created by tohure on 2/03/18.
 */

class FeedAdapter internal constructor() : RecyclerView.Adapter<FeedAdapter.FeedItemViewHolder>() {

    private val feedEntries: MutableList<FeedQuery.FeedEntry>

    init {
        this.feedEntries = ArrayList()
    }

    internal fun addData(feedEntries: List<FeedQuery.FeedEntry>) {
        this.feedEntries.addAll(feedEntries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_feed, parent, false)
        return FeedItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) =
            with(holder.itemView) {

                if (feedEntries[position].postedBy() != null) {
                    lblItem1.text = String.format("@%s", feedEntries[position].postedBy()!!.login())
                } else {
                    lblItem1.text = feedEntries[position].id().toString()
                }

                if (feedEntries[position].repository() != null) {
                    lblItem2.text = feedEntries[position]
                            .repository()?.fragments()?.repositoryFragment()?.full_name()
                } else {
                    lblItem2.text = context.getString(R.string.not_repository)
                }
            }

    override fun getItemCount(): Int = feedEntries.size

    class FeedItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
