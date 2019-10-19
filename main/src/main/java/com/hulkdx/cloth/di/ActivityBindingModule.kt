package com.hulkdx.cloth.di

import com.hulkdx.cloth.view.screens.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import hulkdx.com.features.common.di.annotations.ActivityScoped
import hulkdx.com.features.auth.di.AuthBindingModule
import hulkdx.com.features.category.di.CategoryBindingModule
import hulkdx.com.features.explore.di.ExploreBindingModule
import hulkdx.com.features.profile.di.ProfileBindingModule

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        MainViewModelModule::class,
        // fragments
        ExploreBindingModule::class,
        ProfileBindingModule::class,
        CategoryBindingModule::class,
        AuthBindingModule::class
    ])
    internal abstract fun mainActivity(): MainActivity

}
