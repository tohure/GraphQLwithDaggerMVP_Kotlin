package io.tohure.graphqlwithdaggerkotlin.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import io.tohure.graphqlwithdaggerkotlin.FeedQuery
import io.tohure.graphqlwithdaggerkotlin.R
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : DaggerAppCompatActivity(), FeedContract.View {

    @Inject
    lateinit var presenter: Lazy<FeedPresenter>
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        init()
    }

    private fun init() {
        //init recycler
        rvFeeds.setHasFixedSize(true)
        feedAdapter = FeedAdapter()
        rvFeeds.adapter = feedAdapter

        presenter.get().getFeed(10)
    }

    override fun showLoading() {
        progresBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progresBar.visibility = View.GONE
    }

    override fun showError(error: String) =
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()

    override fun showResult(feedEntries: List<FeedQuery.FeedEntry>) {
        //With Handler way
        feedAdapter.addData(feedEntries)

        //Without Handler way
        //this@FeedActivity.runOnUiThread { feedAdapter.addData(feedEntries) }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.get().detachView()
    }
}
