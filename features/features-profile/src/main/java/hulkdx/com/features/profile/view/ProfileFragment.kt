package hulkdx.com.features.profile.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.util.observeFragment

import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.features.profile.R
import hulkdx.com.features.profile.di.DaggerProfileComponent
import hulkdx.com.features.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */
private const val ACTIVITY_REQUEST_CODE_GALLERY = 0

class ProfileFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mProfileViewModel: ProfileViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        uploadClothButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.uploadClothButton -> {
                startGallery()
            }
        }
    }

    // endregion SetupUI ---------------------------------------------------------------------------
    // region Gallery ------------------------------------------------------------------------------

    private fun startGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(requireContext().packageManager) == null) {
            showError(R.string.gallery_app_not_found)
            return
        }
        startActivityForResult(intent, ACTIVITY_REQUEST_CODE_GALLERY)
    }

    private fun onGalleryResult(successful: Boolean, uri: Uri?) {
        if (!successful) {
            showError(R.string.gallery_app_result_not_successful)
            return
        }
        if (uri == null) {
            showError(R.string.gallery_app_result_uri_null)
            return
        }

        val inputStream = requireContext().contentResolver.openInputStream(uri)
        if (inputStream == null) {
            showError(R.string.gallery_app_result_input_stream_null)
            return
        }
        mProfileViewModel.uploadNewCloth(inputStream)
    }

    // endregion Gallery ---------------------------------------------------------------------------


    override fun setupViewModel() {
        mProfileViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProfileViewModel::class.java)
        mProfileViewModel.uploadClothLiveData().observeFragment(this, Observer {
            // TODO:
        })
    }

    // region Upload Cloth Callback ----------------------------------------------------------------

    // endregion Upload Cloth Callback -------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun inject(context: Context) {
        DaggerProfileComponent.builder()
                .context(context)
                .applicationComponent(applicationComponent(context))
                .build()
                .inject(this)
    }

    override fun fragmentLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ACTIVITY_REQUEST_CODE_GALLERY) {
            val isSuccessful = resultCode == Activity.RESULT_OK
            onGalleryResult(isSuccessful, data?.data)
        }
    }

    private fun showError(stringCode: Int) {
        Toast.makeText(context, stringCode, Toast.LENGTH_SHORT).show()
    }

    // endregion Extra functions -------------------------------------------------------------------
}
    