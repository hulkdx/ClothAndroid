package hulkdx.com.domain.interactor.auth.user

import hulkdx.com.domain.TEST_USER_1
import hulkdx.com.domain.repository.local.UserDatabase
import io.reactivex.Flowable
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
        validUser()
        // Act
        SUT.fetchUserInfo {}
        // Assert
        verify(mUserDatabase).getUserFlowable()
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
        assertTrue(result is GetUserUseCase.Result.ValidUser)
    }

    @Test
    fun fetchUserInfo_invalidUser_callbackFailed() {
        // Arrange
        invalidUser()
        var result: GetUserUseCase.Result? = null
        // Act
        SUT.fetchUserInfo {
            result = it
        }
        // Assert
        assertTrue(result is GetUserUseCase.Result.InvalidUser)
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
        assertTrue(result is GetUserUseCase.Result.InvalidUser)
    }

    // region helper methods -----------------------------------------------------------------------

    private fun runtimeException() {
        `when`(mUserDatabase.getUserFlowable()).thenReturn(Flowable.error(RuntimeException()))
    }

    private fun validUser() {
        `when`(mUserDatabase.getUserFlowable()).thenReturn(Flowable.just(GetUserUseCase.Result.ValidUser(TEST_USER_1)))
    }

    private fun invalidUser() {
        `when`(mUserDatabase.getUserFlowable()).thenReturn(Flowable.just(GetUserUseCase.Result.InvalidUser))
    }

    // endregion helper methods --------------------------------------------------------------------

}