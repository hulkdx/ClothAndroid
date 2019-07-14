package com.hulkdx.cloth.di

import android.content.Context
import com.hulkdx.cloth.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import hulkdx.com.core.android.di.ApplicationComponent
import hulkdx.com.core.android.di.MainActivityScope

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@MainActivityScope
@Component(modules = [
], dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun applicationComponent(applicationComponent: ApplicationComponent): Builder
        fun build(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
