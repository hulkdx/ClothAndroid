package hulkdx.com.data.firebase.storage.upload

import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import hulkdx.com.data.firebase.AsyncToSync
import hulkdx.com.domain.repository.remote.FileUploader
import hulkdx.com.domain.entities.ImageEntity
import java.io.InputStream
import java.lang.Exception
import java.lang.RuntimeException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
class FileUploaderImpl(
        private val mStorageReference: StorageReference
): FileUploader {

    @Throws(Exception::class)
    override fun uploadImage(inputStream: InputStream): ImageEntity {
        inputStream.use { closableStream ->
            // Listen to url by Firebase
            val asyncToSync1 = AsyncToSync<String>()
            mStorageReference.downloadUrl.addOnSuccessListener {
                asyncToSync1.signalAll(it.toString())
            }

            // Uploading the file
            val uploadTask = mStorageReference.putStream(closableStream)
            val asyncToSync = AsyncToSync<FirebaseUploadResult>()
            uploadTask.addOnFailureListener { exception ->
                asyncToSync.signalAll(FirebaseUploadResult(null, exception))
            }.addOnSuccessListener {
                asyncToSync.signalAll(FirebaseUploadResult(it, null))
            }
            val result = asyncToSync.await()
            if (result.exception != null) {
                throw result.exception
            }
            if (result.data == null) {
                throw RuntimeException("upload: result.data is null")
            }
            val result2 = asyncToSync1.await()

            // Convert it to FileMetaData
            return convertToFileMetaData(result.data, result2)
        }
    }

    private fun convertToFileMetaData(taskSnapshot: UploadTask.TaskSnapshot,
                                      url: String): ImageEntity {
        val metadata = taskSnapshot.metadata ?: throw RuntimeException("upload: metadata is null")
        return ImageEntity(
                size = metadata.sizeBytes,
                url = url
        )
    }

    data class FirebaseUploadResult (
            val data: UploadTask.TaskSnapshot?,
            val exception: Exception?
    )
}
