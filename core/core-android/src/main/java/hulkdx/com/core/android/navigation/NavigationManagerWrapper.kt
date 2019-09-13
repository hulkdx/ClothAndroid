package hulkdx.com.core.android.navigation

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/08/2019.
 *
 * The Wrapper Class around NavigationManagerImpl.
 *
 * This class is created to remove the inter-dependency between features (Fragments), for example
 * features-A (FragmentA) wants to launch features-B (FragmentB).
 * This can be done in the followings:
 * 1. feature-A has a dependency on feature-B
 * 2. Using this wrapper as Singleton:
 *      main dependency (MainActivity) will set mNavigationManager in this class, and BaseFragment
 *      will use it to navigate to another fragment.
 *
 */
@Suppress("unused")
@Singleton
class NavigationManagerWrapper @Inject constructor() {

    private lateinit var mNavigationManager: NavigationManager

    fun setNavigationManager(navigationManager: NavigationManager) {
        mNavigationManager = navigationManager
    }

    fun navigateToExplore() {
        navigateTo(NAVIGATE_FEATURE_EXPLORE)
    }

    fun navigateToRegister() {
        navigateTo(NAVIGATE_FEATURE_REGISTER)
    }

    fun navigateToProfile() {
        navigateTo(NAVIGATE_FEATURE_PROFILE)
    }

    fun navigateTo(fragmentId: Int) {
        mNavigationManager.navigateTo(fragmentId)
    }
}
