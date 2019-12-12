package hulkdx.com.features.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.entities.CategoryEntity
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */

internal class ProfileViewModel @Inject constructor(
     private val mUploadClothUseCase: UploadClothUseCase
): ViewModel() {

    private val mUploadClothLiveData = MutableLiveData<UseCaseResult<ClothEntity>>()
    private val mUploadCategoriesSelected = mutableSetOf<CategoryEntity>()

    // region LiveData Setup -----------------------------------------------------------------------

     fun uploadClothLiveData(): LiveData<UseCaseResult<ClothEntity>> = mUploadClothLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    fun uploadNewCloth(inputStream: InputStream, param: UploadClothUseCase.Params) {
        mUploadClothUseCase.upload(inputStream, param) { result ->
            mUploadClothLiveData.value = result
        }
    }

    // region Selected Categories ------------------------------------------------------------------

    fun categorySelected(category: CategoryEntity) {
        if (mUploadCategoriesSelected.contains(category)) {
            mUploadCategoriesSelected.remove(category)
        } else {
            mUploadCategoriesSelected.add(category)
        }
    }

    fun getCategoriesSelected(): Set<CategoryEntity> = mUploadCategoriesSelected

    // endregion Selected Categories ---------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
         mUploadClothUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------

}
