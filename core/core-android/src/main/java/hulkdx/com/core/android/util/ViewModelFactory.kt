package hulkdx.com.core.android.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hulkdx.com.core.android.di.MainActivityScope
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@MainActivityScope
class ViewModelFactory @Inject constructor(
    private val viewModel: ViewModel
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}