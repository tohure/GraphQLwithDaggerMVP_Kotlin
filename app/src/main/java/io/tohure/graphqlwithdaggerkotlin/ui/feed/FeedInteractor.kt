package io.tohure.graphqlwithdaggerkotlin.ui.feed

import android.os.Handler
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloCallback
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import io.tohure.graphqlwithdaggerkotlin.FeedQuery
import io.tohure.graphqlwithdaggerkotlin.type.FeedType
import javax.inject.Inject

/**
 * Created by tohure on 2/03/18.
 */
class FeedInteractor
@Inject
constructor(private val apolloClient: ApolloClient, val handler: Handler) : FeedContract.Interactor {

    private var dataApolloCall: ApolloCall<FeedQuery.Data>? = null

    override fun getFeedFromApollo(limit: Int, callback: FeedContract.Callback) {

        val feedQuery = FeedQuery.builder()
                .limit(limit.toLong())
                .type(FeedType.HOT)
                .build()

        //With Handler way
        dataApolloCall = apolloClient
                .query(feedQuery)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)

        (dataApolloCall as ApolloQueryCall<FeedQuery.Data>)
                .enqueue(ApolloCallback(object : ApolloCall.Callback<FeedQuery.Data>() {
                    override fun onResponse(response: Response<FeedQuery.Data>) {
                        callback.getFeedSuccess(
                                response.data()!!.feedEntries() as List<FeedQuery.FeedEntry>)
                    }

                    override fun onFailure(e: ApolloException) {
                        callback.getFeedError(e.message.toString())
                    }
                }, handler))

        //Without Handler way
        /*apolloClient
                .query(feedQuery)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
                .enqueue(object : ApolloCall.Callback<FeedQuery.Data>() {
                    override fun onResponse(response: Response<FeedQuery.Data>) {
                        callback.getFeedSuccess(
                                response.data()!!.feedEntries() as List<FeedQuery.FeedEntry>)
                    }

                    override fun onFailure(e: ApolloException) {
                        callback.getFeedError(e.message.toString())
                    }
                })*/

    }

    override fun cancelCalls() {
        if (dataApolloCall != null) {
            dataApolloCall!!.cancel()
        }
    }
}