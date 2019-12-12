package hulkdx.com.features.entertainment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/12/2019.
 */

class EntertainmentViewModel @Inject constructor(
        private val mLoadClothUseCase: LoadClothUseCase
): ViewModel() {

    private val mClothLiveData = MutableLiveData<UseCaseResult<ClothEntity>>()

    init {
        loadSingleRandomClothAsync()
    }

    // region LiveData Setup -----------------------------------------------------------------------

    fun clothLiveData(): LiveData<UseCaseResult<ClothEntity>> = mClothLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    fun loadSingleRandomClothAsync() {
        mLoadClothUseCase.loadSingleRandomClothAsync {
            mClothLiveData.value = it
        }
    }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mLoadClothUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region Results ------------------------------------------------------------------------------
    // endregion Results ---------------------------------------------------------------------------

}
