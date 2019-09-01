package hulkdx.com.domain.repository.remote

import hulkdx.com.domain.entities.ImageEntity
import java.io.InputStream
import java.lang.Exception

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
interface FileUploader {

    @Throws(Exception::class)
    fun uploadImage(inputStream: InputStream): ImageEntity

}