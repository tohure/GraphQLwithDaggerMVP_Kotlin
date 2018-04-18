package io.tohure.graphqlwithdaggerkotlin.ui.feed

import io.tohure.graphqlwithdaggerkotlin.FeedQuery
import javax.inject.Inject

/**
 * Created by tohure on 2/03/18.
 */

class FeedPresenter
@Inject
constructor(var view: FeedContract.View?, val interactor: FeedInteractor) :
        FeedContract.Presenter,
        FeedContract.Callback {

    override fun getFeed(limit: Int) {
        view?.showLoading()
        interactor.getFeedFromApollo(limit, this)
    }

    override fun detachView() {
        interactor.cancelCalls()
        view = null
    }

    override fun getFeedSuccess(feedEntries: List<FeedQuery.FeedEntry>) {
        view?.hideLoading()
        view?.showResult(feedEntries)
    }

    override fun getFeedError(error: String) {
        view?.hideLoading()
        view?.showError(error)
    }

}
