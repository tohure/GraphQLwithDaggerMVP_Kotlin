package io.tohure.graphqlwithdaggerkotlin.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.tohure.graphqlwithdaggerkotlin.di.anotation.PerActivity
import io.tohure.graphqlwithdaggerkotlin.di.module.ActivityModule
import io.tohure.graphqlwithdaggerkotlin.di.module.GraphModule
import javax.inject.Singleton

@PerActivity
@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (GraphModule::class),
    (ActivityModule::class)])
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
