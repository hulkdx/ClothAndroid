package hulkdx.com.core.android.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

inline fun <reified VM: ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelFactory): VM {
    return ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
}

inline fun <reified VM: ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelFactory): VM {
    return ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
}