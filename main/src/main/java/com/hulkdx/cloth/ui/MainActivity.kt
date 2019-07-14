package com.hulkdx.cloth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hulkdx.cloth.R
import hulkdx.com.core.android.applicationComponent
import com.hulkdx.cloth.di.DaggerMainActivityComponent
import hulkdx.com.features.home.ui.HomeFragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class MainActivity: AppCompatActivity() {

    // region Lifecycle ----------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()

        configureFragments(savedInstanceState == null)
    }

    // endregion Lifecycle -------------------------------------------------------------------------
    // endregion Fragments -------------------------------------------------------------------------

    private fun configureFragments(isFirstTime: Boolean) {
        if (isFirstTime) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, HomeFragment())
                    .commit()
        }
    }

    // region Fragments ----------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    private fun inject() {
        DaggerMainActivityComponent
                .builder()
                .context(this)
                .applicationComponent(applicationComponent(this))
                .build()
                .inject(this)
    }

    // endregion Extra -----------------------------------------------------------------------------

}