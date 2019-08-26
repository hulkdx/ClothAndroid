package com.hulkdx.cloth.view.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hulkdx.cloth.navigation.NavigationManagerImpl
import com.hulkdx.cloth.R
import hulkdx.com.core.android.applicationComponent
import com.hulkdx.cloth.di.DaggerMainActivityComponent
import hulkdx.com.core.android.navigation.NavigationManagerWrapper
import hulkdx.com.core.android.viewmodel.AuthCommonViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class MainActivity: AppCompatActivity() {

    @Inject lateinit var mNavigationManagerWrapper: NavigationManagerWrapper
    @Inject lateinit var mNavigationManager: NavigationManagerImpl
    @Inject lateinit var mAuthCommonViewModel: AuthCommonViewModel

    // region Lifecycle ----------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        mNavigationManagerWrapper.setNavigationManager(mNavigationManager)

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
    // region Extra --------------------------------------------------------------------------------

    private fun inject() {
        DaggerMainActivityComponent
                .builder()
                .activity(this)
                .applicationComponent(applicationComponent(this))
                .build()
                .inject(this)
    }

    // endregion Extra -----------------------------------------------------------------------------

}