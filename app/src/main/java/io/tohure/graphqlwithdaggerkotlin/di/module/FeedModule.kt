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
    fun provideFeedView(): FeedContract.View = view

    @Provides
    fun provideMainHandler(): Handler = Handler(Looper.getMainLooper())

    @Provides
    fun provideFeedInteractor(apolloClient: ApolloClient, handler: Handler): FeedInteractor =
            FeedInteractor(apolloClient, handler)


    @Provides
    fun provideFeedPresenter(view: FeedContract.View, interactor: FeedInteractor): FeedPresenter =
            FeedPresenter(view, interactor)

}
