package hulkdx.com.features.profile.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.model.CoreUserLiveData
import hulkdx.com.core.android.util.ImageLoader
import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.core.android.viewmodel.CoreViewModel
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.features.profile.R
import hulkdx.com.features.profile.di.getProfileComponent
import hulkdx.com.features.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile_main.*
import javax.inject.Inject
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.RectF
import android.graphics.Path
import hulkdx.com.core.android.util.CircleColorDrawable
import hulkdx.com.domain.entities.UserType


/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */

class MainProfileFragment : BaseFragment() {

    @Inject lateinit var mImageLoader: ImageLoader
    private lateinit var mProfileViewModel: ProfileViewModel
    private lateinit var mCoreViewModel: CoreViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        mProfileViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProfileViewModel::class.java)
        mCoreViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CoreViewModel::class.java)

        // UserInfo will be loaded on MainActivity (once the application is firstly loaded)
        mCoreViewModel.getUserLiveData().observe(this, Observer {
            onUserChanged(it)
        })
    }

    // region User Changed -------------------------------------------------------------------------

    private fun onUserChanged(result: CoreUserLiveData.Result) {
        when (result) {
            is CoreUserLiveData.Result.Loading     -> onUserLoading()
            is CoreUserLiveData.Result.ValidUser   -> onUserValid(result.user)
            is CoreUserLiveData.Result.InvalidUser -> onUserInvalid()
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

    override fun inject(context: Context) {
        getProfileComponent(context)
                .inject(this)
    }

    override fun fragmentLayout(): Int {
        return R.layout.fragment_profile_main
    }

    // endregion Extra functions -------------------------------------------------------------------

}
