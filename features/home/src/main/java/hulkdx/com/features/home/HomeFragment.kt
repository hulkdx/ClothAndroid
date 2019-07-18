package hulkdx.com.features.home

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.ui.base.BaseFragment
import hulkdx.com.features.home.di.DaggerHomeComponent
import hulkdx.com.features.home.adapter.ClothAdapter
import hulkdx.com.features.home.model.ClothModelViewHolder
import hulkdx.com.features.home.viewmodel.HomeViewModel
import hulkdx.com.features.home.viewmodel.results.ClothesViewModelResults
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class HomeFragment: BaseFragment<HomeViewModel>() {

    private lateinit var mClothAdapter: ClothAdapter

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        clothRecyclerView.layoutManager = layoutManager
        mClothAdapter = ClothAdapter()
        clothRecyclerView.adapter = mClothAdapter
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        mViewModel.getClothes().observe(this, Observer {
            when (it) {
                is ClothesViewModelResults.Success      -> loadClothesSuccess(it.clothes)
                is ClothesViewModelResults.NetworkError -> loadClothesNetworkError()
                is ClothesViewModelResults.GeneralError -> loadClothesGeneralError()
                is ClothesViewModelResults.AuthError    -> authError()
            }
        })
    }

    // region Load Clothes -------------------------------------------------------------------------

    private fun loadClothesSuccess(clothes: List<ClothModelViewHolder>) {
        mClothAdapter.updateClothes(clothes)
        mClothAdapter.notifyDataSetChanged()
    }

    private fun loadClothesNetworkError() {
        // TODO What happens?
    }

    private fun loadClothesGeneralError() {
        // TODO What happens?
    }

    // endregion Load Clothes ----------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun inject(context: Context) {
        DaggerHomeComponent.builder()
                .context(context)
                .applicationComponent(applicationComponent(context))
                .build()
                .inject(this)
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun fragmentLayout(): Int  = R.layout.fragment_home

    private fun authError() {
        // TODO logout
    }

    // endregion Extra functions -------------------------------------------------------------------

}