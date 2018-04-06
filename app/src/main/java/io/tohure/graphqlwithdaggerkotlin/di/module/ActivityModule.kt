package io.tohure.graphqlwithdaggerkotlin.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.tohure.graphqlwithdaggerkotlin.ui.FeedActivity
import io.tohure.graphqlwithdaggerkotlin.ui.FeedActivityModule

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(FeedActivityModule::class)])
    internal abstract fun bindFeedActivity(): FeedActivity

}