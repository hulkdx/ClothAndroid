package hulkdx.com.features.entertainment.view

import android.view.View
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.features.common.util.ImageLoader

import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.features.entertainment.R
import hulkdx.com.features.entertainment.viewmodel.EntertainmentViewModel
import kotlinx.android.synthetic.main.fragment_entertainment.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/12/2019.
 */
class EntertainmentFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var mImageLoader: ImageLoader
    private lateinit var mEntertainmentViewModel: EntertainmentViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        likeButton.setOnClickListener(this)
        rejectButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            rejectButton,
            likeButton -> {
                mEntertainmentViewModel.loadSingleRandomClothAsync()
            }
        }
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        mEntertainmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EntertainmentViewModel::class.java)
        mEntertainmentViewModel.clothLiveData().observeUseCase {
            updateCloth(it)
        }
    }

    // region Cloth Callback ------------------------------------------------------------------------

    private fun updateCloth(clothEntity: ClothEntity) {
        mImageLoader.loadImage(clothEntity.image.url, clothImageView)
    }

    // endregion Cloth Callback ---------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int {
        return R.layout.fragment_entertainment
    }

    // endregion Extra functions -------------------------------------------------------------------
}
    