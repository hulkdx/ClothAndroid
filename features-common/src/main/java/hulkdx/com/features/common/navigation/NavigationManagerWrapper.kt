package hulkdx.com.features.common.navigation

import hulkdx.com.features.common.model.FragmentType
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

    private var mNavigationManager: NavigationManager? = null

    fun setNavigationManager(navigationManager: NavigationManager?) {
        mNavigationManager = navigationManager
    }

    fun navigateToExplore() {
        navigateTo(NAVIGATE_FEATURE_ENTERTAINMENT)
    }

    fun navigateToRegister(addToBackStack: Boolean) {
        navigateTo(NAVIGATE_FEATURE_REGISTER, addToBackStack)
    }

    fun navigateToProfile() {
        navigateTo(NAVIGATE_FEATURE_PROFILE)
    }

    fun navigateToLogin() {
        navigateTo(NAVIGATE_FEATURE_LOGIN)
    }

    fun navigateToCategory() {
        navigateTo(NAVIGATE_FEATURE_CATEGORY)
    }

    fun getFragmentType(fragmentId: Int?): Int {
        return when (fragmentId) {
            NAVIGATE_FEATURE_ENTERTAINMENT  -> FragmentType.TYPE_EXPLORE
            NAVIGATE_FEATURE_REGISTER -> FragmentType.TYPE_PROFILE
            NAVIGATE_FEATURE_LOGIN    -> FragmentType.TYPE_PROFILE
            NAVIGATE_FEATURE_PROFILE  -> FragmentType.TYPE_PROFILE
            NAVIGATE_FEATURE_CATEGORY -> FragmentType.TYPE_CATEGORY
            else -> FragmentType.TYPE_UNKNOWN
        }
    }

    private fun navigateTo(fragmentId: Int, addToBackStack: Boolean = false) {
        mNavigationManager?.navigateTo(fragmentId, addToBackStack)
    }
}
