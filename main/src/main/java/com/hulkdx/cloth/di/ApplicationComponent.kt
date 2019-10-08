package com.hulkdx.cloth.di

import android.content.Context
import com.hulkdx.cloth.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import hulkdx.com.core.android.di.SchedulerModule
import hulkdx.com.data.cache.di.CacheModule
import hulkdx.com.data.database.di.DatabaseModule
import hulkdx.com.data.database.di.DatabaseBindsModule
import hulkdx.com.data.firebase.di.RemoteBindsModule
import hulkdx.com.data.firebase.di.RemoteModule
import hulkdx.com.domain.di.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    SchedulerModule::class,
    ActivityBindingModule::class,
    DatabaseBindsModule::class,
    DatabaseModule::class,
    CacheModule::class,
    RemoteModule::class,
    RemoteBindsModule::class
])
interface ApplicationComponent: AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        @ApplicationContext
        fun applicationContext(@ApplicationContext context: Context): Builder
        fun build(): ApplicationComponent
    }

}


