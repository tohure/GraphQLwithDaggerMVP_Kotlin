package io.tohure.graphqlwithdaggerkotlin.ui.feed

import android.os.Handler
import android.os.Looper
import com.apollographql.apollo.ApolloClient
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by tohure on 2/03/18.
 */

@Module
abstract class FeedActivityModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideMainHandler() = Handler(Looper.getMainLooper())

        @Provides
        @JvmStatic
        fun provideFeedInteractor(
                apolloClient: ApolloClient,
                handler: Handler) = FeedInteractor(apolloClient, handler)

        @Provides
        @JvmStatic
        fun provideFeedPresenter(
                view: FeedContract.View,
                interactor: FeedInteractor) = FeedPresenter(view, interactor)
    }

    @Binds
    abstract fun provideFeedView(mainActivity: FeedActivity): FeedContract.View
}