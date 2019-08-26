package hulkdx.com.domain.interactor.cloth.upload

import com.nhaarman.mockitokotlin2.verify
import hulkdx.com.domain.data.remote.FileUploader
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.omg.CORBA.Object
import java.io.FileInputStream

import java.io.InputStream
import java.lang.Exception

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
@Suppress("PrivatePropertyName")
class UploadClothUseCaseImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    @Mock lateinit var mFileUploader: FileUploader

    @Mock lateinit var mParamsInputStream: InputStream

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: UploadClothUseCaseImpl

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = UploadClothUseCaseImpl(trampoline, trampoline, mFileUploader)
    }

    @Test
    fun upload_shouldCallFileUploader() {
        // Arrange
        // Act
        SUT.upload(mParamsInputStream) {}
        // Assert
        verify(mFileUploader).upload(mParamsInputStream)
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}