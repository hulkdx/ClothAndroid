package hulkdx.com.core.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hulkdx.com.core.android.di.MainActivityScope
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
class ViewModelFactory @Inject constructor(
    private val viewModel: ViewModel,
    private val authViewModel: AuthViewModel
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AuthViewModel::class.java -> authViewModel  as T
            else                      -> viewModel      as T
        }
    }

}