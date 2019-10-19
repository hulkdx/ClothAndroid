package com.hulkdx.cloth.view.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hulkdx.cloth.navigation.NavigationManagerImpl
import com.hulkdx.cloth.R
import dagger.android.*
import hulkdx.com.features.common.navigation.NavigationManagerWrapper
import hulkdx.com.features.common.util.ViewModelFactory
import hulkdx.com.features.common.viewmodel.CoreViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class MainActivity: AppCompatActivity(), HasAndroidInjector {
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var mNavigationManagerWrapper: NavigationManagerWrapper
    @Inject lateinit var mNavigationManager: NavigationManagerImpl
    @Inject lateinit var mViewModelFactory: ViewModelFactory
    
    lateinit var mCoreViewModel: CoreViewModel

    // region Lifecycle ----------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavigationManagerWrapper.setNavigationManager(mNavigationManager)

        mCoreViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CoreViewModel::class.java)
        if (savedInstanceState == null) {
            mCoreViewModel.fetchUserInfo()
        }

        configureFragments(savedInstanceState == null)
    }

    // endregion Lifecycle -------------------------------------------------------------------------
    // endregion Fragments -------------------------------------------------------------------------

    private fun configureFragments(isFirstTime: Boolean) {
        if (isFirstTime) {
            mNavigationManager.startFirstFragment()
        }
    }

    // region Fragments ----------------------------------------------------------------------------

}