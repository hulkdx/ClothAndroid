package hulkdx.com.features.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.ui.base.BaseFragment
import hulkdx.com.core.android.util.observeFragment
import hulkdx.com.core.android.viewmodel.AuthViewModel
import hulkdx.com.features.home.adapter.ClothAdapter
import hulkdx.com.features.home.di.DaggerExploreComponent
import hulkdx.com.features.home.model.Cloth
import hulkdx.com.features.home.viewmodel.ExploreViewModel
import hulkdx.com.features.home.viewmodel.results.ClothesViewModelResults
import kotlinx.android.synthetic.main.fragment_explore.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class ExploreFragment: BaseFragment() {

    @Inject internal lateinit var mClothAdapter: ClothAdapter

    private lateinit var mAuthViewModel: AuthViewModel
    private lateinit var mExploreViewModel: ExploreViewModel

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
        mExploreViewModel = mViewModelHelper.getViewModel(ExploreViewModel::class.java)
        mAuthViewModel = mViewModelHelper.getViewModel(AuthViewModel::class.java)

        mExploreViewModel.loadClothes()
        mExploreViewModel.getClothes().observeFragment(this, Observer {
            when (it) {
                is ClothesViewModelResults.Success      -> loadClothesSuccess(it.clothes)
                is ClothesViewModelResults.NetworkError -> loadClothesNetworkError()
                is ClothesViewModelResults.GeneralError -> loadClothesGeneralError()
                is ClothesViewModelResults.AuthError    -> authError()
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
        mAuthViewModel.logout()
    }

    // endregion Extra functions -------------------------------------------------------------------

}