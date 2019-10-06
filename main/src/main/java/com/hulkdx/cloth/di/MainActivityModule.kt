package com.hulkdx.cloth.di

import androidx.fragment.app.FragmentManager
import com.hulkdx.cloth.view.screens.MainActivity
import dagger.Module
import dagger.Provides

@Module
object MainActivityModule {

    @Provides
    @JvmStatic
    fun provideFragmentManager(activity: MainActivity): FragmentManager = activity.supportFragmentManager

}
