package hulkdx.com.domain.repository.remote

import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
interface AddClothEndPoint {

    fun addCloth(user: UserEntity,
                 image: ImageEntity,
                 params: UploadClothUseCase.Params): UseCaseResult<ClothEntity>

}
