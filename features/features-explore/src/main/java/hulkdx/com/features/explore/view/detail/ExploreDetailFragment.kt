package hulkdx.com.features.explore.view.detail

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.util.ImageLoader
import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.core.android.viewmodel.AuthCommonViewModel
import hulkdx.com.features.explore.R
import hulkdx.com.features.explore.di.DaggerExploreComponent
import hulkdx.com.features.explore.di.getExploreComponent
import hulkdx.com.features.explore.model.Cloth
import kotlinx.android.synthetic.main.fragment_explore_detail.*
import java.lang.RuntimeException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 08/09/2019.
 */
class ExploreDetailFragment: BaseFragment() {

    private lateinit var mAuthCommonViewModel: AuthCommonViewModel
    private lateinit var mCloth: Cloth
    private lateinit var mImageLoader: ImageLoader

    // region Statics ------------------------------------------------------------------------------

    companion object {

        private const val ARGUMENT_CLOTH = "0"

        fun newInstance(cloth: Cloth): ExploreDetailFragment {
            val fragment = ExploreDetailFragment()

            val bundle = Bundle()
            bundle.putParcelable(ARGUMENT_CLOTH, cloth)

            fragment.arguments = bundle

            return fragment
        }
    }

    // endregion Statics ---------------------------------------------------------------------------
    // region Lifecycle ----------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCloth = arguments?.getParcelable(ARGUMENT_CLOTH)
                ?: throw RuntimeException("argument is null")
    }

    // endregion Lifecycle -------------------------------------------------------------------------
    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        mImageLoader.loadImage(mCloth.imageUrl, clothImageView)
        priceTextView.text = mCloth.price
    }

    override fun setupViewModel() {
        mAuthCommonViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthCommonViewModel::class.java)
    }

    // endregion SetupUI ---------------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun inject(context: Context) {
        getExploreComponent(context)
                .inject(this)
    }

    override fun fragmentLayout(): Int  = R.layout.fragment_explore_detail

    // endregion Extra functions -------------------------------------------------------------------

}
