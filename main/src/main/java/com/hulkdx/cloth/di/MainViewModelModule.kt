package com.hulkdx.cloth.di

import dagger.Module
import hulkdx.com.features.common.di.CoreViewModelModule

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */
@Suppress("unused")
@Module(includes = [
    CoreViewModelModule::class
])
abstract class MainViewModelModule {

}
    