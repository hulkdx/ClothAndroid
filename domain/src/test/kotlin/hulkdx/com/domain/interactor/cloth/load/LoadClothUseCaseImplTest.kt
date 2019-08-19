package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.TEST_CLOTHES
import hulkdx.com.domain.data.remote.GetClothesEndPoint
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ClothesEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import io.reactivex.schedulers.Schedulers

import org.junit.Assert.*
import org.mockito.Mockito.*
import org.hamcrest.CoreMatchers.*
import java.io.IOException
import java.util.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
@Suppress("HasPlatformType")
class LoadClothUseCaseImplTest {

    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()
    @Mock lateinit var mClothApiManager: GetClothesEndPoint

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: LoadClothUseCaseImpl

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = LoadClothUseCaseImpl(trampoline, trampoline, mClothApiManager)
    }

    @Test
    fun loadAsync_callApi() {
        // Arrange
        // Act
        SUT.loadAsync({}, {}, {}, {})
        // Assert
        verify(mClothApiManager).getClothes()
    }

    @Test
    fun loadAsync_success_resultSuccess() {
        // Arrange
        success()
        var result: List<ClothEntity>? = null
        // Act
        SUT.loadAsync({
            result = it
        }, {}, {}, {})
        // Assert
        assertThat(result, `is`(TEST_CLOTHES))
    }

//    TODO:
//    @Test
//    fun loadAsync_ioException_resultNetworkError() {
//        // Arrange
//        ioException()
//        var result = false
//        // Act
//        SUT.loadAsync(onSuccess = {}, onNetworkError = {
//            result = true
//        }, onGeneralError = {}, onAuthError = {})
//        // Assert
//        assertThat(result, `is`(true))
//    }
//
//    @Test
//    fun loadAsync_generalException_resultGeneralException() {
//        // Arrange
//        generalException()
//        var result = false
//        // Act
//        SUT.loadAsync(onSuccess = {}, onNetworkError = {}, onGeneralError = {
//            result = true
//        }, onAuthError = {})
//        // Assert
//        assertThat(result, `is`(true))
//    }
//
//    @Test
//    fun loadAsync_authException_resultGeneralException() {
//        // Arrange
//        authException()
//        var result = false
//        // Act
//        SUT.loadAsync(onSuccess = {}, onNetworkError = {}, onGeneralError = {}, onAuthError = {
//            result = true
//        })
//        // Assert
//        assertThat(result, `is`(true))
//    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        `when`(mClothApiManager.getClothes()).thenReturn(ClothesEntity(TEST_CLOTHES, Date()))
    }

    private fun ioException() {
        `when`(mClothApiManager.getClothes()).thenThrow(IOException(""))
    }

    private fun generalException() {
        `when`(mClothApiManager.getClothes()).thenThrow(Exception(""))
    }

    private fun authException() {
        `when`(mClothApiManager.getClothes()).thenThrow(AuthException())
    }

    // endregion helper methods --------------------------------------------------------------------

}