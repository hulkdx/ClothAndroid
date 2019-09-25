package hulkdx.com.core.android.navigation

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/08/2019.
 */

const val NAVIGATE_FEATURE_EXPLORE = 1
const val NAVIGATE_FEATURE_REGISTER = 2
const val NAVIGATE_FEATURE_PROFILE = 3
const val NAVIGATE_FEATURE_LOGIN = 4

interface NavigationManager {
    fun navigateTo(navigationId: Int)
}
