package hulkdx.com.domain.interactor.auth.user

import hulkdx.com.domain.TEST_USER_1
import hulkdx.com.domain.anyKotlin
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.domain.repository.local.UserDatabase
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
 * Created by Mohammad Jafarzadeh Rezvan on 24/09/2019.
 */
@Suppress("PrivatePropertyName")
class GetUserUseCaseImplTest {

    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------
    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: GetUserUseCaseImpl
    @Mock lateinit var mUserDatabase: UserDatabase

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = GetUserUseCaseImpl(trampoline, trampoline, mUserDatabase)
    }

    @Test
    fun fetchUserInfo_passItToEndpoint() {
        // Arrange
        // Act
        SUT.fetchUserInfo {}
        // Assert
        verify(mUserDatabase).getUser()
    }

    @Test
    fun fetchUserInfo_validUser_callbackSuccess() {
        // Arrange
        validUser()
        var result: GetUserUseCase.Result? = null
        // Act
        SUT.fetchUserInfo {
            result = it
        }
        // Assert
        assertTrue(result is GetUserUseCase.Result.Success)
    }

    @Test
    fun fetchUserInfo_nullUser_callbackFailed() {
        // Arrange
        nullUser()
        var result: GetUserUseCase.Result? = null
        // Act
        SUT.fetchUserInfo {
            result = it
        }
        // Assert
        assertTrue(result is GetUserUseCase.Result.Failed)
    }

    @Test
    fun fetchUserInfo_exception_callbackFailed() {
        // Arrange
        runtimeException()
        var result: GetUserUseCase.Result? = null
        // Act
        SUT.fetchUserInfo {
            result = it
        }
        // Assert
        assertTrue(result is GetUserUseCase.Result.Failed)
    }

    // region helper methods -----------------------------------------------------------------------

    private fun runtimeException() {
        `when`(mUserDatabase.getUser()).thenThrow(RuntimeException())
    }

    private fun validUser() {
        `when`(mUserDatabase.getUser()).thenReturn(TEST_USER_1)
    }

    private fun nullUser() {
        `when`(mUserDatabase.getUser()).thenReturn(null)
    }

    // endregion helper methods --------------------------------------------------------------------

}