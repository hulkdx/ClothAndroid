package hulkdx.com.features.common.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.features.common.R
import hulkdx.com.features.common.model.FragmentType
import hulkdx.com.features.common.navigation.NavigationManagerWrapper
import hulkdx.com.features.common.util.ViewModelFactory
import hulkdx.com.features.common.util.observeFragment
import hulkdx.com.features.common.viewmodel.CoreViewModel
import kotlinx.android.synthetic.main.layout_footer.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

abstract class BaseFragment: Fragment() {

    @Inject protected lateinit var mViewModelFactory: ViewModelFactory
    @Inject protected lateinit var mNavigationManager: NavigationManagerWrapper
    protected lateinit var mCoreViewModel: CoreViewModel

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
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupViewModel()
        return inflater.inflate(fragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFooter(view)
        setupUI()
    }

    // endregion LifeCycle -------------------------------------------------------------------------
    // region abstract functions -------------------------------------------------------------------

    protected abstract fun fragmentLayout(): Int
    protected abstract fun setupUI()

    @CallSuper
    protected open fun setupViewModel() {
        mCoreViewModel = ViewModelProviders.of(requireActivity(), mViewModelFactory).get(CoreViewModel::class.java)
    }

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
                footerExploreButton.isClickable = false
            }
            FragmentType.TYPE_CATEGORY -> {
                footerCategoryButton.isPressed = true
                footerCategoryButton.isClickable = false
            }
            FragmentType.TYPE_PROFILE -> {
                footerProfileButton.isPressed = true
                footerProfileButton.isClickable = false
            }
        }
    }

    private fun onFooterExploreButtonClicked() {
        mNavigationManager.navigateToExplore()
    }

    private fun onFooterProfileButtonClicked() {
        mNavigationManager.navigateToProfile()
    }

    private fun onCategoryButtonClicked() {
        mNavigationManager.navigateToCategory()
    }

    // endregion Setup Footer / Header -------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    private fun getNavigationId(): Int? {
        return tag?.toInt()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region UseCase Common Error -----------------------------------------------------------------

    private fun authError() {
        mCoreViewModel.logout()
    }

    private fun networkError() {
        TODO()
    }

    private fun generalError() {

    }

    protected fun <V: Any, T: UseCaseResult<V>> LiveData<T>.observeUseCase(
            callback: (V) -> Unit
    ) {
        observeFragment(this@BaseFragment, Observer {
            when (it) {
                is UseCaseResult.Success<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    callback(it.data as V)
                }
                is UseCaseResult.AuthError -> {
                    authError()
                }
                is UseCaseResult.NetworkError -> {
                    networkError()
                }
                is UseCaseResult.GeneralError -> {
                    generalError()
                }
            }
        })
    }

    // endregion UseCase Common Error --------------------------------------------------------------

}
