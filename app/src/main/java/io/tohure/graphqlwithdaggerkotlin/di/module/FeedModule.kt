package io.tohure.graphqlwithdaggerkotlin.di.module

import android.os.Handler
import android.os.Looper
import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.tohure.graphqlwithdaggerkotlin.ui.FeedContract
import io.tohure.graphqlwithdaggerkotlin.ui.FeedInteractor
import io.tohure.graphqlwithdaggerkotlin.ui.FeedPresenter

/**
 * Created by tohure on 2/03/18.
 */
@Module
class FeedModule(private val view: FeedContract.View) {

    @Provides
    internal fun provideFeedView(): FeedContract.View {
        return view
    }

    @Provides
    internal fun provideMainHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }

    @Provides
    internal fun provideFeedInteractor(
            apolloClient: ApolloClient,
            handler: Handler): FeedInteractor {
        return FeedInteractor(apolloClient, handler)
    }

    @Provides
    internal fun provideFeedPresenter(
            view: FeedContract.View,
            interactor: FeedInteractor): FeedPresenter {
        return FeedPresenter(view, interactor)
    }
}
