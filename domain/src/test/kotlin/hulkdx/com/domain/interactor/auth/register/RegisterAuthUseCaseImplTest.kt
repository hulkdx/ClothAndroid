package hulkdx.com.domain.interactor.auth.register

import hulkdx.com.domain.anyKotlin
import hulkdx.com.domain.data.remote.RegisterEndPoint
import hulkdx.com.domain.entities.UserGender
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import org.junit.Assert.*
import org.mockito.Mockito.*
import java.lang.RuntimeException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/08/2019.
 */
@Suppress("PrivatePropertyName")
class RegisterAuthUseCaseImplTest {

    // region constants ----------------------------------------------------------------------------

    private val TEST_PARAM = RegisterAuthUseCase.Params(
            "",
            "",
            "",
            "",
            UserGender.Male
    )

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: RegisterAuthUseCaseImpl
    @Mock lateinit var mRegisterEndPoint: RegisterEndPoint

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = RegisterAuthUseCaseImpl(trampoline, trampoline, mRegisterEndPoint)
    }

    @Test
    fun register_passItToEndpoint() {
        // Arrange
        // Act
        SUT.register(TEST_PARAM) {}
        // Assert
        verify(mRegisterEndPoint).register(TEST_PARAM)
    }

    @Test
    fun register_exception_generalError() {
        // Arrange
        runtimeException()
        var result: RegisterAuthUseCase.Result? = null
        // Act
        SUT.register(TEST_PARAM) {
            result = it
        }
        // Assert
        assertTrue(result is RegisterAuthUseCase.Result.GeneralError)
    }

    @Test
    fun register_success_callbackSuccess() {
        // Arrange
        success()
        var result: RegisterAuthUseCase.Result? = null
        // Act
        SUT.register(TEST_PARAM) {
            result = it
        }
        // Assert
        assertTrue(result is RegisterAuthUseCase.Result.Success)
    }

    // region helper methods -----------------------------------------------------------------------

    private fun runtimeException() {
        `when`(mRegisterEndPoint.register(anyKotlin())).thenThrow(RuntimeException())
    }

    private fun success() {
        `when`(mRegisterEndPoint.register(anyKotlin())).thenReturn(RegisterAuthUseCase.Result.Success())
    }

    // endregion helper methods --------------------------------------------------------------------

}