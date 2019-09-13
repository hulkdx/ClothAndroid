package hulkdx.com.core.android.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hulkdx.com.core.android.R
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
        }
    }

    // region LifeCycle ----------------------------------------------------------------------------

    override fun onAttach(context: Context?) {
        inject(requireContext())
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
        rootView.findViewById<View>(R.id.footerRootLinearLayout) ?: return

        footerExploreButton.setOnClickListener(baseFragmentClickListeners)
        footerProfileButton.setOnClickListener(baseFragmentClickListeners)
    }

    private fun onFooterExploreButtonClicked() {
        navigateTo(NAVIGATE_FEATURE_EXPLORE)
    }

    private fun onFooterProfileButtonClicked() {
        navigateTo(NAVIGATE_FEATURE_PROFILE)
    }

    // endregion Setup Footer / Header -------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    private fun navigateTo(fragmentId: Int) {
        if (isCurrentFragmentTypeOf(fragmentId)) {
            return
        }
        mNavigationManager.navigateTo(fragmentId)
    }

    private fun isCurrentFragmentTypeOf(fragmentId: Int): Boolean {
        return tag != null && tag == fragmentId.toString()
    }

    // endregion Extra -----------------------------------------------------------------------------

}
