package hulkdx.com.features.explore.view.list

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.core.android.util.observeFragment
import hulkdx.com.core.android.viewmodel.AuthCommonViewModel
import hulkdx.com.features.explore.R
import hulkdx.com.features.explore.di.DaggerExploreComponent
import hulkdx.com.features.explore.model.Cloth
import hulkdx.com.features.explore.viewmodel.ExploreViewModel
import hulkdx.com.features.explore.viewmodel.ExploreViewModel.ClothesResults.*
import kotlinx.android.synthetic.main.fragment_explore.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class ExploreListFragment: BaseFragment(), ClothAdapter.ClickListener {

    @Inject internal lateinit var mClothAdapter: ClothAdapter

    private lateinit var mAuthCommonViewModel: AuthCommonViewModel
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
        mExploreViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ExploreViewModel::class.java)
        mAuthCommonViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthCommonViewModel::class.java)

        mExploreViewModel.loadClothes()
        mExploreViewModel.getClothes().observeFragment(this, Observer {
            when (it) {
                is Success      -> loadClothesSuccess(it.clothes)
                is NetworkError -> loadClothesNetworkError()
                is GeneralError -> loadClothesGeneralError()
                is AuthError    -> authError()
            }
        })
    }

    // region Load Clothes -------------------------------------------------------------------------

    private fun loadClothesSuccess(clothes: List<Cloth>) {
        mClothAdapter.updateClothes(clothes)
        mClothAdapter.notifyDataSetChanged()
    }

    private fun loadClothesNetworkError() {
        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
    }

    private fun loadClothesGeneralError() {
        Toast.makeText(context, "General Error", Toast.LENGTH_SHORT).show()
    }

    // endregion Load Clothes ----------------------------------------------------------------------
    // region ClothAdapter Listeners ---------------------------------------------------------------

    override fun onClothClicked(position: Int, cloth: Cloth) {
        // TODO: GO TO DETAIL FRAGMENT
        // mNavigationManager.navigateToExplore()
    }

    // endregion ClothAdapter Listeners ------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun inject(context: Context) {
        DaggerExploreComponent.builder()
                .context(context)
                .applicationComponent(applicationComponent(context))
                .build()
                .inject(this)
    }

    override fun fragmentLayout(): Int  = R.layout.fragment_explore

    private fun authError() {
        mAuthCommonViewModel.logout()
    }

    // endregion Extra functions -------------------------------------------------------------------
}
