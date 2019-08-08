package hulkdx.com.core.android.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 07/08/2019.
 */
class ViewModelHelper @Inject constructor(
        private val mViewModelFactory: ViewModelFactory,
        private val mFragment: Fragment
) {

    fun <T: ViewModel> getViewModel(viewModelClass: Class<T>): T {
        return ViewModelProviders.of(mFragment, mViewModelFactory).get(viewModelClass)
    }

}