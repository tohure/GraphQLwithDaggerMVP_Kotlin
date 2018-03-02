package io.tohure.graphqlwithdaggerkotlin.di.component

import dagger.Component
import io.tohure.graphqlwithdaggerkotlin.ui.FeedActivity
import io.tohure.graphqlwithdaggerkotlin.di.anotation.PerActivity
import io.tohure.graphqlwithdaggerkotlin.di.module.FeedModule
import io.tohure.graphqlwithdaggerkotlin.di.module.GraphModule
import javax.inject.Singleton

/**
 * Created by tohure on 2/03/18.
 */

@PerActivity
@Singleton
@Component(modules = [
    FeedModule::class,
    GraphModule::class])
interface FeedComponent {
    fun inject(activity: FeedActivity)
}
