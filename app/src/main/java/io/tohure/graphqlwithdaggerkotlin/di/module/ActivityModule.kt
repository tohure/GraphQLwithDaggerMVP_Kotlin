package io.tohure.graphqlwithdaggerkotlin.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.tohure.graphqlwithdaggerkotlin.ui.feed.FeedActivity
import io.tohure.graphqlwithdaggerkotlin.ui.feed.FeedActivityModule

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(FeedActivityModule::class)])
    abstract fun bindFeedActivity(): FeedActivity

}
