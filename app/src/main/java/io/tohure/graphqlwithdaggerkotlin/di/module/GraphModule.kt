package io.tohure.graphqlwithdaggerkotlin.di.module

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.tohure.graphqlwithdaggerkotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by tohure on 2/03/18.
 */

@Module
class GraphModule {

    @Provides
    @Singleton
    internal fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
                .serverUrl(BuildConfig.BASE_URL)
                .okHttpClient(okHttpClient)
                .build()
    }
}
