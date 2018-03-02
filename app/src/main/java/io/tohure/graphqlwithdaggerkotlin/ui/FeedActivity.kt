package io.tohure.graphqlwithdaggerkotlin.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import dagger.Lazy
import io.tohure.graphqlwithdaggerkotlin.FeedQuery
import io.tohure.graphqlwithdaggerkotlin.R
import io.tohure.graphqlwithdaggerkotlin.di.module.FeedModule
import io.tohure.graphqlwithdaggerkotlin.di.module.GraphModule
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : AppCompatActivity(), FeedContract.View {

    @Inject
    private lateinit var presenter: Lazy<FeedPresenter>

    private lateinit var feedAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        init()
    }

    private fun init() {
        setInyection()

        //init recycler
        rvFeeds.setHasFixedSize(true)
        feedAdapter = FeedAdapter()
        rvFeeds.adapter = feedAdapter

        presenter.get().getFeed(10)
    }

    private fun setInyection() {
        DaggerFeedComponent.builder()
                .feedModule(FeedModule(this))
                .graphModule(GraphModule())
                .build()
                .inject(this)
    }

    override fun showLoading() {
        progresBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progresBar.visibility = View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showResult(feedEntries: List<FeedQuery.FeedEntry>) {
        //With Handler way
        feedAdapter.addData(feedEntries)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.get().detachView()
    }
}
