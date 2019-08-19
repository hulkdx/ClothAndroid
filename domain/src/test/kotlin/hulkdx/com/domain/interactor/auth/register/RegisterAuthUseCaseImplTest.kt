package hulkdx.com.domain.interactor.auth.register

import hulkdx.com.domain.anyKotlin
import hulkdx.com.domain.data.remote.RegisterEndPoint
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
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import java.lang.RuntimeException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/08/2019.
 */
class RegisterAuthUseCaseImplTest {

    // region constants ----------------------------------------------------------------------------
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
        val param = RegisterAuthUseCase.Params("", "", "", "")
        // Act
        SUT.register(param) {}
        // Assert
        verify(mRegisterEndPoint).register(param)
    }

    @Test
    fun register_exception_generalError() {
        // Arrange
        runtimeException()
        val param = RegisterAuthUseCase.Params("", "", "", "")
        var result: RegisterAuthUseCase.Result? = null
        // Act
        SUT.register(param) {
            result = it
        }
        // Assert
        assertTrue(result is RegisterAuthUseCase.Result.GeneralError)
    }

    @Test
    fun register_success_callbackSuccess() {
        // Arrange
        success()
        val param = RegisterAuthUseCase.Params("", "", "", "")
        var result: RegisterAuthUseCase.Result? = null
        // Act
        SUT.register(param) {
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