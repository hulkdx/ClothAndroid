package hulkdx.com.features.explore.view.list

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import hulkdx.com.features.common.util.observeFragment
import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.features.explore.R
import hulkdx.com.features.explore.model.Cloth
import hulkdx.com.features.explore.view.detail.ExploreDetailFragment
import hulkdx.com.features.explore.viewmodel.ExploreViewModel
import hulkdx.com.features.explore.viewmodel.ExploreViewModel.ClothesResults.*
import kotlinx.android.synthetic.main.fragment_explore_list.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class ExploreListFragment: BaseFragment(), ClothAdapter.ClickListener {

    @Inject internal lateinit var mClothAdapter: ClothAdapter

    private lateinit var mExploreViewModel: ExploreViewModel

    // region Lifecycle ----------------------------------------------------------------------------

    override fun onResume() {
        super.onResume()
        mClothAdapter.registerClickListener(this)
    }

    override fun onPause() {
        super.onPause()
        mClothAdapter.unregisterClickListener()
    }

    // endregion Lifecycle -------------------------------------------------------------------------
    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        clothRecyclerView.layoutManager = layoutManager
        clothRecyclerView.adapter = mClothAdapter
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        mExploreViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ExploreViewModel::class.java)

        mExploreViewModel.loadClothes()
        mExploreViewModel.getClothes().observeFragment(this, Observer {
            when (it) {
                is Success      -> loadClothesSuccess(it.clothes)
//                is NetworkError -> loadClothesNetworkError()
//                is GeneralError -> loadClothesGeneralError()
//                is AuthError    -> authError()
            }
        })
    }

    // region Load Clothes -------------------------------------------------------------------------

    private fun loadClothesSuccess(clothes: List<Cloth>) {
        mClothAdapter.updateClothes(clothes)
        mClothAdapter.notifyDataSetChanged()
    }

    // endregion Load Clothes ----------------------------------------------------------------------
    // region ClothAdapter Listeners ---------------------------------------------------------------

    override fun onClothClicked(position: Int, cloth: Cloth) {
        val fragment = ExploreDetailFragment.newInstance(cloth)

        requireFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment, null)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    // endregion ClothAdapter Listeners ------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int  = R.layout.fragment_explore_list

    // endregion Extra functions -------------------------------------------------------------------
}
