package hulkdx.com.features.entertainment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/12/2019.
 */

class EntertainmentViewModel @Inject constructor(
    // private val mWhatNameUseCase: WhatNameUseCase
): ViewModel() {

    // private val mWhatNameLiveData = MutableLiveData<WhatNameUseCase.Result>()

    // region LiveData Setup -----------------------------------------------------------------------

    // fun WhatNameLiveData(): LiveData<WhatNameUseCase.Result> = mWhatNameLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    // fun somefunction() {
    //     mWhatNameUseCase.somefunction(
    //             callback = {
    //                 mWhatNameLiveData.value = it
    //             })
    // }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        // mWhatNameUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region Results ------------------------------------------------------------------------------
    // endregion Results ---------------------------------------------------------------------------

}