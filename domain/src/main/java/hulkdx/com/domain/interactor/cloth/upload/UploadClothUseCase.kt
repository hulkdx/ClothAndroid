package hulkdx.com.domain.interactor.cloth.upload

import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ImageEntity
import java.io.InputStream

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
interface UploadClothUseCase {

    fun upload(inputStream: InputStream,
               params: Params,
               callback: (Result) -> (Unit))

    fun dispose()

    data class Params (
            val price: Float,
            val currency: String
    )

    sealed class Result {
        data class Success(val cloth: ClothEntity): Result()
        object AuthError: Result()
        data class GeneralError(val throwable: Throwable): Result()
    }
}
