package com.hulkdx.cloth.di

import androidx.appcompat.app.AppCompatActivity
import com.hulkdx.cloth.view.screens.MainActivity
import dagger.BindsInstance
import dagger.Component
import hulkdx.com.core.android.di.ApplicationComponent
import hulkdx.com.core.android.di.annotations.MainActivityScope

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@MainActivityScope
@Component(modules = [
    MainActivityModule::class
], dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun applicationComponent(applicationComponent: ApplicationComponent): Builder
        fun build(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
