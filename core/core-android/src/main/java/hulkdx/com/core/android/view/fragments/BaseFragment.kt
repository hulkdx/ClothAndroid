package hulkdx.com.core.android.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hulkdx.com.core.android.R
import hulkdx.com.core.android.model.FragmentType
import hulkdx.com.core.android.navigation.NAVIGATE_FEATURE_EXPLORE
import hulkdx.com.core.android.navigation.NAVIGATE_FEATURE_PROFILE
import hulkdx.com.core.android.navigation.NavigationManagerWrapper
import hulkdx.com.core.android.util.ViewModelFactory
import kotlinx.android.synthetic.main.layout_footer.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

abstract class BaseFragment: Fragment() {

    @Inject
    protected lateinit var mViewModelFactory: ViewModelFactory

    @Inject
    protected lateinit var mNavigationManager: NavigationManagerWrapper

    private val baseFragmentClickListeners = View.OnClickListener { view ->
        when (view.id) {
            R.id.footerExploreButton -> {
                onFooterExploreButtonClicked()
            }
            R.id.footerProfileButton -> {
                onFooterProfileButtonClicked()
            }
            R.id.footerCategoryButton -> {
                onCategoryButtonClicked()
            }
        }
    }

    // region LifeCycle ----------------------------------------------------------------------------

    override fun onAttach(context: Context) {
        inject(context)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFooter(view)
        setupUI()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
    }

    // endregion LifeCycle -------------------------------------------------------------------------
    // region abstract functions -------------------------------------------------------------------

    protected abstract fun inject(context: Context)
    protected abstract fun fragmentLayout(): Int
    protected abstract fun setupUI()
    protected abstract fun setupViewModel()

    // endregion abstract functions ----------------------------------------------------------------
    // region Setup Footer / Header ----------------------------------------------------------------

    private fun setupFooter(rootView: View) {
        rootView.findViewById<View>(R.id.footerExploreButton) ?: return

        val type = mNavigationManager.getFragmentType(getNavigationId())

        // Click listeners
        if (type != FragmentType.TYPE_EXPLORE) {
            footerExploreButton.setOnClickListener(baseFragmentClickListeners)
        }
        if (type != FragmentType.TYPE_CATEGORY) {
            footerCategoryButton.setOnClickListener(baseFragmentClickListeners)
        }
        if (type != FragmentType.TYPE_PROFILE) {
            footerProfileButton.setOnClickListener(baseFragmentClickListeners)
        }

        // isPressed to show the pressed image
        when (type) {
            FragmentType.TYPE_EXPLORE -> {
                footerExploreButton.isPressed = true
            }
            FragmentType.TYPE_CATEGORY -> {
                footerCategoryButton.isPressed = true
            }
            FragmentType.TYPE_PROFILE -> {
                footerProfileButton.isPressed = true
            }
        }
    }

    private fun onFooterExploreButtonClicked() {
        navigateTo(NAVIGATE_FEATURE_EXPLORE)
    }

    private fun onFooterProfileButtonClicked() {
        navigateTo(NAVIGATE_FEATURE_PROFILE)
    }

    private fun onCategoryButtonClicked() {
    }

    // endregion Setup Footer / Header -------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    private fun navigateTo(navigationId: Int) {
        mNavigationManager.navigateTo(navigationId)
    }

    private fun getNavigationId(): Int? {
        return tag?.toInt()
    }

    // endregion Extra -----------------------------------------------------------------------------

}
