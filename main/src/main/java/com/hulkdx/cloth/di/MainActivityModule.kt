package com.hulkdx.cloth.di

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.hulkdx.cloth.view.screens.MainActivity
import dagger.Module
import dagger.Provides
import hulkdx.com.domain.di.ActivityContext

@Module
object MainActivityModule {

    @Provides
    @JvmStatic
    fun provideFragmentManager(activity: MainActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    @JvmStatic
    @ActivityContext
    fun provideActivityContext(activity: MainActivity): Context = activity

}
