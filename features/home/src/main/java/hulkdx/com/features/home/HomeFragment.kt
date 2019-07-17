package hulkdx.com.features.home

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.ui.base.BaseFragment
import hulkdx.com.features.home.di.DaggerHomeComponent
import hulkdx.com.features.home.adapter.ClothAdapter
import hulkdx.com.features.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class HomeFragment: BaseFragment<HomeViewModel>() {

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        clothRecyclerView.layoutManager = layoutManager
        clothRecyclerView.adapter = ClothAdapter()
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
    }

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

    // endregion Extra functions -------------------------------------------------------------------

}