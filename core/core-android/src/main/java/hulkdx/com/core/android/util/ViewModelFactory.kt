package hulkdx.com.core.android.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.RuntimeException

import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Mohammad Jafarzadeh Rezvan on 11/08/2019.
 */
class ViewModelFactory @Inject constructor(
        private val mProviderMap: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mProviderMap[modelClass]?.get() as T
    }
}
