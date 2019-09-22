package hulkdx.com.core.android.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/07/2019.
 *
 * This class is not singleton. Add a singleton dependency if it needs one.
 */
class CoreViewModel @Inject constructor(): ViewModel() {

    fun logout() {
        // TODO
    }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
    }

    // endregion Extra -----------------------------------------------------------------------------

}