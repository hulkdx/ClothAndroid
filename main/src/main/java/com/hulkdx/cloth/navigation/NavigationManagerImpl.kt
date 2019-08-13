package com.hulkdx.cloth.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hulkdx.cloth.R
import hulkdx.com.core.android.navigation.*
import hulkdx.com.core.android.navigation.NavigationManager
import hulkdx.com.features.explore.view.list.ExploreListFragment
import java.lang.RuntimeException
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/08/2019.
 *
 * For more information read: NavigationManagerWrapper.kt
 */
class NavigationManagerImpl @Inject constructor(
        private val fragmentManager: FragmentManager
): NavigationManager {

    private val containerId = R.id.container

    override fun navigateTo(fragmentId: Int) {
        val fragment = getFragment(fragmentId)

        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss()
    }

    fun startFirstFragment() {
        fragmentManager.beginTransaction()
                .add(containerId, ExploreListFragment())
                .commit()
    }

    private fun getFragment(fragmentId: Int): Fragment {
        when (fragmentId) {
            NAVIGATE_FEATURE_EXPLORE -> {
                return ExploreListFragment()
            }
            else -> {
                throw RuntimeException("NavigationManagerImpl: cannot find the fragment")
            }
        }
    }

}