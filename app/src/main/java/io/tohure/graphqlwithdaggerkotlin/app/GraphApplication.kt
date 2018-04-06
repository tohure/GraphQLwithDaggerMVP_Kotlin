package io.tohure.graphqlwithdaggerkotlin.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.tohure.graphqlwithdaggerkotlin.di.component.DaggerAppComponent

class GraphApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
