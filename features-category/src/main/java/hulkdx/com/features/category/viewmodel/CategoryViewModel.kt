package hulkdx.com.features.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.domain.interactor.cloth.load.LoadClothWithCategoryUseCase
import hulkdx.com.domain.entities.Category
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/10/2019.
 */

class CategoryViewModel @Inject constructor(
     private val mLoadClothWithCategoryUseCase: LoadClothWithCategoryUseCase
): ViewModel() {

     private val mCategoryLiveData = MutableLiveData<UseCaseResult<List<Category>>>()

    // region LiveData Setup -----------------------------------------------------------------------

     fun categoryLiveData(): LiveData<UseCaseResult<List<Category>>> = mCategoryLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    fun loadCategory() {
        mLoadClothWithCategoryUseCase.loadAsync { result ->
            mCategoryLiveData.value = result
        }
    }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mLoadClothWithCategoryUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------

}
