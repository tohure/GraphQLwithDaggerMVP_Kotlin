package io.tohure.graphqlwithdaggerkotlin.ui.feed

import io.tohure.graphqlwithdaggerkotlin.FeedQuery

/**
 * Created by tohure on 2/03/18.
 */

interface FeedContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun showError(error: String)

        fun showResult(feedEntries: List<FeedQuery.FeedEntry>)

    }

    interface Presenter {

        fun getFeed(limit: Int)

        fun detachView()

    }

    interface Interactor {

        fun getFeedFromApollo(limit: Int, callback: Callback)

        fun cancelCalls()

    }

    interface Callback {

        fun getFeedSuccess(feedEntries: List<FeedQuery.FeedEntry>)

        fun getFeedError(error: String)

    }
}
