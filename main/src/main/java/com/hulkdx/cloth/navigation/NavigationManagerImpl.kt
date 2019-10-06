package com.hulkdx.cloth.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import hulkdx.com.core.android.navigation.*
import hulkdx.com.core.android.navigation.NavigationManager
import hulkdx.com.features.auth.view.login.LoginFragment
import hulkdx.com.features.auth.view.register.RegisterFragment
import hulkdx.com.features.explore.view.list.ExploreListFragment
import hulkdx.com.features.profile.view.ProfileFragment
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

    private val containerId = android.R.id.content

    fun startFirstFragment() {
        fragmentManager.beginTransaction()
                .add(containerId, ExploreListFragment(), NAVIGATE_FEATURE_EXPLORE.toString())
                .commit()
    }

    override fun navigateTo(navigationId: Int, addToBackStack: Boolean) {
        val fragment = getFragment(navigationId)

        val transaction = fragmentManager.beginTransaction()
                .replace(containerId, fragment, navigationId.toString())

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commitAllowingStateLoss()
    }

    private fun getFragment(fragmentId: Int): Fragment {
        return when (fragmentId) {
            NAVIGATE_FEATURE_EXPLORE -> {
                ExploreListFragment()
            }
            NAVIGATE_FEATURE_REGISTER -> {
                RegisterFragment()
            }
            NAVIGATE_FEATURE_PROFILE -> {
                ProfileFragment()
            }
            NAVIGATE_FEATURE_LOGIN -> {
                LoginFragment()
            }
            else -> {
                throw RuntimeException("NavigationManagerImpl: cannot find the fragment")
            }
        }
    }

}