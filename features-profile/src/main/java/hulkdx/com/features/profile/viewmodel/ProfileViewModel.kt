package hulkdx.com.features.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */

internal class ProfileViewModel @Inject constructor(
     private val mUploadClothUseCase: UploadClothUseCase
): ViewModel() {

     private val mUploadClothLiveData = MutableLiveData<UploadClothUseCase.Result>()

    // region LiveData Setup -----------------------------------------------------------------------

     fun uploadClothLiveData(): LiveData<UploadClothUseCase.Result> = mUploadClothLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    fun uploadNewCloth(inputStream: InputStream, param: UploadClothUseCase.Params) {
        mUploadClothUseCase.upload(inputStream, param) { result ->
            mUploadClothLiveData.value = result
        }
    }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
         mUploadClothUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region Results ------------------------------------------------------------------------------
    // endregion Results ---------------------------------------------------------------------------

}
