package hulkdx.com.features.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import hulkdx.com.features.home.ui.viewmodel.results.ClothesViewModelResults
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class HomeViewModel @Inject constructor(
        private val mLoadClothUseCase: LoadClothUseCase
): ViewModel() {

    private val clothes = MutableLiveData<ClothesViewModelResults>()

    init {
        loadClothes()
    }

    // region LiveData Setup -----------------------------------------------------------------------

    fun getClothes(): LiveData<ClothesViewModelResults> = clothes

    // endregion LiveData Setup --------------------------------------------------------------------
    // region Clothes ------------------------------------------------------------------------------

    private fun loadClothes() {
        mLoadClothUseCase.loadAsync {

        }
    }

    // endregion Clothes ---------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
    }

    // endregion Extra -----------------------------------------------------------------------------

}