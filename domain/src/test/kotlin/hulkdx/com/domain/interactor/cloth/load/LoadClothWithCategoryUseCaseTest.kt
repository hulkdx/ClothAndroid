@file:Suppress("PrivatePropertyName")

package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.entities.Category
import hulkdx.com.domain.entities.ClothWithCategoryEntity
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.repository.remote.GetClothesEndPoint
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.ArgumentCaptor
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import org.junit.Assert.*
import org.mockito.Mockito.*
import org.hamcrest.CoreMatchers.*
import org.mockito.ArgumentMatchers.*
import java.io.IOException
import java.util.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/12/2019.
 */
class LoadClothWithCategoryUseCaseTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule var mMockitoJUnit = MockitoJUnit.rule()!!

    @Mock lateinit var mGetClothesEndPoint: GetClothesEndPoint

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: LoadClothWithCategoryUseCase

    @Before
    fun setup() {
        val testScheduler = Schedulers.trampoline()
        SUT = LoadClothWithCategoryUseCase(testScheduler, testScheduler, mGetClothesEndPoint)
    }


    @Test
    fun loadAsync_callApi() {
        // Arrange
        // Act
        SUT.loadAsync {}
        // Assert
        verify(mGetClothesEndPoint).getCategory()
    }

    @Test
    fun loadAsync_success_resultSuccess() {
        // Arrange
        success()
        var result: UseCaseResult<List<Category>>? = null
        // Act
        SUT.loadAsync {
            result = it
        }
        // Assert
        assertTrue(result is UseCaseResult.Success)
    }

    @Test
    fun loadAsync_ioException_resultNetworkError() {
        // Arrange
        ioException()
        var result: UseCaseResult<List<Category>>? = null
        // Act
        SUT.loadAsync {
            result = it
        }
        // Assert
        assertTrue(result is UseCaseResult.NetworkError)
    }

    @Test
    fun loadAsync_generalException_resultGeneralException() {
        // Arrange
        generalException()
        var result: UseCaseResult<List<Category>>? = null
        // Act
        SUT.loadAsync {
            result = it
        }
        // Assert
        assertTrue(result is UseCaseResult.GeneralError)
    }

    @Test
    fun loadAsync_authException_resultGeneralException() {
        // Arrange
        authException()
        var result: UseCaseResult<List<Category>>? = null
        // Act
        SUT.loadAsync {
            result = it
        }
        // Assert
        assertTrue(result is UseCaseResult.AuthError)
    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        `when`(mGetClothesEndPoint.getCategory()).thenReturn(listOf())
    }

    private fun ioException() {
        `when`(mGetClothesEndPoint.getCategory()).thenThrow(IOException(""))
    }

    private fun generalException() {
        `when`(mGetClothesEndPoint.getCategory()).thenThrow(Exception(""))
    }

    private fun authException() {
        `when`(mGetClothesEndPoint.getCategory()).thenThrow(AuthException())
    }

    // endregion helper methods --------------------------------------------------------------------

}
