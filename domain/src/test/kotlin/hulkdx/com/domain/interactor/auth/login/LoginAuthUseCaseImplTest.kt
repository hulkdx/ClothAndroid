package hulkdx.com.domain.interactor.auth.login

import hulkdx.com.domain.TEST_USER_1
import hulkdx.com.domain.anyKotlin
import hulkdx.com.domain.interactor.auth.login.LoginAuthUseCase.Result
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.LoginEndPoint
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
import java.lang.RuntimeException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/09/2019.
 */
@Suppress("PrivatePropertyName")
class LoginAuthUseCaseImplTest {
    // region constants ----------------------------------------------------------------------------

    private val USERNAME = "username"
    private val PASSWORD = "password"

    // endregion constants -------------------------------------------------------------------------
    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!
    @Mock lateinit var mUserDatabase: UserDatabase
    @Mock lateinit var mLoginEndPoint: LoginEndPoint

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: LoginAuthUseCaseImpl

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = LoginAuthUseCaseImpl(trampoline, trampoline, mUserDatabase, mLoginEndPoint)
    }

    @Test
    fun login_passItToEndpoint() {
        // Arrange
        // Act
        SUT.login(USERNAME, PASSWORD) {}
        // Assert
        verify(mLoginEndPoint).login(USERNAME, PASSWORD)
    }

    @Test
    fun login_exception_generalError() {
        // Arrange
        runtimeException()
        var result: Result? = null
        // Act
        SUT.login(USERNAME, PASSWORD) {
            result = it
        }
        // Assert
        assertTrue(result is Result.GeneralError)
    }

    @Test
    fun login_success_callbackSuccess() {
        // Arrange
        success()
        var result: Result? = null
        // Act
        SUT.login(USERNAME, PASSWORD) {
            result = it
        }
        // Assert
        assertTrue(result is Result.Success)
    }

    @Test
    fun login_success_saveToDatabase() {
        // Arrange
        success()
        // Act
        SUT.login(USERNAME, PASSWORD) {}
        // Assert
        verify(mUserDatabase).saveUser(TEST_USER_1)
    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        `when`(mLoginEndPoint.login(anyKotlin(), anyKotlin())).thenReturn(Result.Success(TEST_USER_1))
    }

    private fun runtimeException() {
        `when`(mLoginEndPoint.login(anyKotlin(), anyKotlin())).thenThrow(RuntimeException())
    }

    // endregion helper methods --------------------------------------------------------------------

}
