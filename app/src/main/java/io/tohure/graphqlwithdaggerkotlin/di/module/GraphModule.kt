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
    fun provideInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor()
                    .setLevel(
                            if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE)

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()

    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient =
            ApolloClient.builder()
                    .serverUrl(BuildConfig.BASE_URL)
                    .okHttpClient(okHttpClient)
                    .build()
}
