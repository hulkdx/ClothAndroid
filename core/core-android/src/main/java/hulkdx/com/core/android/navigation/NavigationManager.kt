package hulkdx.com.core.android.navigation

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/08/2019.
 */

const val NAVIGATE_FEATURE_EXPLORE = 1

interface NavigationManager {
    fun navigateTo(fragmentId: Int)
}
