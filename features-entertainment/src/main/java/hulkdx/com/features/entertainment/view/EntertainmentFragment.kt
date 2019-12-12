package hulkdx.com.features.entertainment.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.features.common.util.observeFragment

import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.features.entertainment.R
import hulkdx.com.features.entertainment.viewmodel.EntertainmentViewModel
import kotlinx.android.synthetic.main.fragment_entertainment.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/12/2019.
 */
class EntertainmentFragment : BaseFragment() {

    private lateinit var mEntertainmentViewModel: EntertainmentViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        mEntertainmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EntertainmentViewModel::class.java)
        // mEntertainmentViewModel.TODOLiveData().observeFragment(this, Observer {
        //     when (it) {
        //     }
        // })
    }

    // region TODO Callback ------------------------------------------------------------------------

    // endregion TODO Callback ---------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int {
        return R.layout.fragment_entertainment
    }

    // endregion Extra functions -------------------------------------------------------------------
}
    