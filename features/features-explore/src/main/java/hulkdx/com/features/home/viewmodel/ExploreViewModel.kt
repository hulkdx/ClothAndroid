package hulkdx.com.features.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import hulkdx.com.features.home.mapper.ClothMapper
import hulkdx.com.features.home.viewmodel.results.ClothesViewModelResults
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class ExploreViewModel @Inject constructor(
        private val mLoadClothUseCase: LoadClothUseCase,
        private val mClothMapper:      ClothMapper
): ViewModel() {

    private val mClothesLiveData = MutableLiveData<ClothesViewModelResults>()

    // region LiveData Setup -----------------------------------------------------------------------

    fun getClothes(): LiveData<ClothesViewModelResults> = mClothesLiveData

    // endregion LiveData Setup --------------------------------------------------------------------
    // region Clothes ------------------------------------------------------------------------------

    fun loadClothes() {
        mLoadClothUseCase.loadAsync(onSuccess = {
            mClothesLiveData.value = ClothesViewModelResults.Success(mClothMapper.mapListClothes(it))
        }, onAuthError = {
            Log.e("ExploreViewModel", "AuthError: $it")
            mClothesLiveData.value = ClothesViewModelResults.AuthError
        }, onGeneralError = {
            Log.e("ExploreViewModel", "GeneralError: $it")
            mClothesLiveData.value = ClothesViewModelResults.GeneralError
        }, onNetworkError = {
            Log.e("ExploreViewModel", "NetworkError: $it")
            mClothesLiveData.value = ClothesViewModelResults.NetworkError
        })
    }

    // endregion Clothes ---------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mLoadClothUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------

}