package hulkdx.com.features.category.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.features.common.util.observeFragment

import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.features.category.R
import hulkdx.com.features.category.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/10/2019.
 */
class CategoryFragment : BaseFragment() {

    private lateinit var mCategoryViewModel: CategoryViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        mCategoryViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CategoryViewModel::class.java)
        // mCategoryViewModel.TODOLiveData().observeFragment(this, Observer {
        //     when (it) {
        //     }
        // })
    }

    // region TODO Callback ------------------------------------------------------------------------

    // endregion TODO Callback ---------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int {
        return R.layout.fragment_category
    }

    // endregion Extra functions -------------------------------------------------------------------
}
    