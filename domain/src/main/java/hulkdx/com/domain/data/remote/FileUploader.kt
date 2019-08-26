package hulkdx.com.domain.data.remote

import java.io.InputStream
import java.lang.Exception

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
interface FileUploader {

    @Throws(Exception::class)
    fun upload(inputStream: InputStream): FileMetaData

    data class FileMetaData (
        val size: Long,
        val url:  String
    )
}