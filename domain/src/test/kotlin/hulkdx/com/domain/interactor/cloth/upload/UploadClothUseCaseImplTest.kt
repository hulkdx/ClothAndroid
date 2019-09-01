package hulkdx.com.domain.interactor.cloth.upload

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import hulkdx.com.domain.CLOTH_IMAGE_1
import hulkdx.com.domain.TEST_USER_1
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.AddClothEndPoint
import hulkdx.com.domain.repository.remote.FileUploader
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import java.io.InputStream

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
@Suppress("PrivatePropertyName")
class UploadClothUseCaseImplTest {

    // region constants ----------------------------------------------------------------------------

    private val UPLOAD_PARAMS = UploadClothUseCase.Params(0F, "")

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    @Mock lateinit var mFileUploader: FileUploader
    @Mock lateinit var mUserDatabase: UserDatabase
    @Mock lateinit var mAddClothEndPoint: AddClothEndPoint

    @Mock lateinit var mParamsInputStream: InputStream

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: UploadClothUseCaseImpl

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = UploadClothUseCaseImpl(
                trampoline,
                trampoline,
                mUserDatabase,
                mAddClothEndPoint,
                mFileUploader
        )
    }

    @Test
    fun upload_getUserFromDatabase() {
        // Arrange
        // Act
        SUT.upload(mParamsInputStream, UPLOAD_PARAMS) {}
        // Assert
        verify(mUserDatabase).get()
    }

    @Test
    fun upload_userNull_returnAuthError() {
        // Arrange
        userNull()
        var result: UploadClothUseCase.Result? = null
        // Act
        SUT.upload(mParamsInputStream, UPLOAD_PARAMS) {
            result = it
        }
        // Assert
        assertTrue(result is UploadClothUseCase.Result.AuthError)
    }

    @Test
    fun upload_userNotNull_shouldCallFileUploader() {
        // Arrange
        userNotNull()
        // Act
        SUT.upload(mParamsInputStream, UPLOAD_PARAMS) {}
        // Assert
        verify(mFileUploader).uploadImage(mParamsInputStream)
    }

    @Test
    fun upload_userNotNullAndUploadSuccess_callAddClothEndPoint() {
        // Arrange
        userNotNull()
        uploadSuccess()
        // Act
        SUT.upload(mParamsInputStream, UPLOAD_PARAMS) {}
        // Assert
        verify(mAddClothEndPoint).addCloth(any(), any(), any())
    }

    // region helper methods -----------------------------------------------------------------------

    private fun userNull() {
        whenever(mUserDatabase.get()).thenReturn(null)
    }

    private fun userNotNull() {
        whenever(mUserDatabase.get()).thenReturn(TEST_USER_1)
    }

    private fun uploadSuccess() {
        whenever(mFileUploader.uploadImage(any())).thenReturn(CLOTH_IMAGE_1)
    }

    // endregion helper methods --------------------------------------------------------------------

}