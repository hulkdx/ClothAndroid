package com.hulkdx.cloth.di

import androidx.lifecycle.ViewModel
import dagger.*
import dagger.multibindings.IntoMap
import hulkdx.com.core.android.di.annotations.ViewModelKey
import hulkdx.com.core.android.viewmodel.CoreViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */
@Suppress("unused")
@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoreViewModel::class)
    abstract fun providesCoreViewModel(viewModel: CoreViewModel): ViewModel

}
    