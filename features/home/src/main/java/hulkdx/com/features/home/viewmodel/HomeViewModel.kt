package hulkdx.com.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import hulkdx.com.features.home.mapper.ClothModelViewHolderMapper
import hulkdx.com.features.home.viewmodel.results.ClothesViewModelResults
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class HomeViewModel @Inject constructor(
        private val mLoadClothUseCase: LoadClothUseCase,
        private val mClothMapper:      ClothModelViewHolderMapper
): ViewModel() {

    private val mClothesLiveData = MutableLiveData<ClothesViewModelResults>()

    init {
        loadClothes()
    }

    // region LiveData Setup -----------------------------------------------------------------------

    fun getClothes(): LiveData<ClothesViewModelResults> = mClothesLiveData

    // endregion LiveData Setup --------------------------------------------------------------------
    // region Clothes ------------------------------------------------------------------------------

    private fun loadClothes() {
        mLoadClothUseCase.loadAsync(onSuccess = {
            mClothesLiveData.value = ClothesViewModelResults.Success(mClothMapper.mapListClothes(it))
        }, onAuthError = {
            mClothesLiveData.value = ClothesViewModelResults.AuthError
        }, onGeneralError = {
            mClothesLiveData.value = ClothesViewModelResults.GeneralError
        }, onNetworkError = {
            mClothesLiveData.value = ClothesViewModelResults.NetworkError
        })
    }

    // endregion Clothes ---------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
    }

    // endregion Extra -----------------------------------------------------------------------------

}