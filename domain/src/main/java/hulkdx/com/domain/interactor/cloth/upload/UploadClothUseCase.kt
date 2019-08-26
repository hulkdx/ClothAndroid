package hulkdx.com.domain.interactor.cloth.upload

import java.io.InputStream

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
interface UploadClothUseCase {

    fun upload(inputStream: InputStream, callback: (Result) -> (Unit))

    fun dispose()

    class Result {

    }
}
