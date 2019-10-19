package hulkdx.com.features.profile.view

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.features.common.util.ImageLoader
import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.features.profile.R
import hulkdx.com.features.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject
import hulkdx.com.features.common.util.CircleColorDrawable
import hulkdx.com.domain.entities.UserType
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase
import hulkdx.com.features.common.util.observeFragment


/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */

class ProfileFragment : BaseFragment() {

    @Inject lateinit var mImageLoader: ImageLoader
    private lateinit var mProfileViewModel: ProfileViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        mProfileViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProfileViewModel::class.java)

        // UserInfo will be loaded on MainActivity (once the application is firstly loaded)
        mCoreViewModel.getUserLiveData().observeFragment(this, Observer {
            onUserChanged(it)
        })
    }

    // region User Changed -------------------------------------------------------------------------

    private fun onUserChanged(result: GetUserUseCase.Result) {
        when (result) {
            is GetUserUseCase.Result.Loading     -> onUserLoading()
            is GetUserUseCase.Result.ValidUser   -> onUserValid(result.user)
            is GetUserUseCase.Result.InvalidUser -> onUserInvalid()
        }
    }

    private fun onUserLoading() {
        loadingProgressBar.visibility = View.VISIBLE
        body.visibility = View.GONE
    }

    private fun onUserValid(user: UserEntity) {
        loadingProgressBar.visibility = View.GONE
        body.visibility = View.VISIBLE
        // Show user info
        val placeholderDrawable = CircleColorDrawable(ContextCompat.getColor(requireContext(), R.color.black))
        val url = user.image?.url ?: ""
        mImageLoader.loadImage(url, userImageView, isCircleCropTransform = true,
                placeholderDrawable = placeholderDrawable)

        userNameTextView.text = user.firstName

        if (user.type == UserType.ADMIN || user.type == UserType.SELLER) {
            uploadNewClothButton.visibility = View.VISIBLE
            uploadNewClothButton.setOnClickListener {
                requireFragmentManager().beginTransaction()
                        .replace(android.R.id.content, NewClothProfileFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
            }
        }
    }

    private fun onUserInvalid() {
        mNavigationManager.navigateToLogin()
    }

    // endregion User Changed ----------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int {
        return R.layout.fragment_profile
    }

    // endregion Extra functions -------------------------------------------------------------------

}
