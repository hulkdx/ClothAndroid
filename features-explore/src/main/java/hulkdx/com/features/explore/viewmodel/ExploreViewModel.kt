package hulkdx.com.features.explore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import hulkdx.com.features.explore.mapper.ClothMapper
import hulkdx.com.features.explore.model.Cloth
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

internal class ExploreViewModel @Inject constructor(
        private val mLoadClothUseCase: LoadClothUseCase,
        private val mClothMapper:      ClothMapper
): ViewModel() {

    private val mClothesLiveData = MutableLiveData<ClothesResults>()

    // region LiveData Setup -----------------------------------------------------------------------

    fun getClothes(): LiveData<ClothesResults> = mClothesLiveData

    // endregion LiveData Setup --------------------------------------------------------------------
    // region Clothes ------------------------------------------------------------------------------

    fun loadClothes() {
        mLoadClothUseCase.loadAsync(onSuccess = {
            mClothesLiveData.value = ClothesResults.Success(mClothMapper.mapListClothes(it))
        }, onAuthError = {
            Log.e("ExploreViewModel", "AuthError: $it")
            mClothesLiveData.value = ClothesResults.AuthError
        }, onGeneralError = {
            Log.e("ExploreViewModel", "GeneralError: $it")
            mClothesLiveData.value = ClothesResults.GeneralError
        }, onNetworkError = {
            Log.e("ExploreViewModel", "NetworkError: $it")
            mClothesLiveData.value = ClothesResults.NetworkError
        })
    }

    // endregion Clothes ---------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mLoadClothUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region Results ------------------------------------------------------------------------------

    sealed class ClothesResults {
        class Success(val clothes: List<Cloth>): ClothesResults()
        object NetworkError: ClothesResults()
        object GeneralError: ClothesResults()
        object AuthError: ClothesResults()
    }

    // endregion Results ---------------------------------------------------------------------------

}
