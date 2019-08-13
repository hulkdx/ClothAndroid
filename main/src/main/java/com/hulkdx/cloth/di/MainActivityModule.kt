package com.hulkdx.cloth.di

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
object MainActivityModule {

    @Provides
    @JvmStatic
    fun provideFragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager

}
