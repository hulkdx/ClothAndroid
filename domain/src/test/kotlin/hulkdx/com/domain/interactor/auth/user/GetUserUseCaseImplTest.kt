package hulkdx.com.domain.interactor.auth.user

import hulkdx.com.domain.TEST_USER_1
import hulkdx.com.domain.entities.CategoryEntity
import hulkdx.com.domain.exception.InvalidUserException
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.CategoryEndPoint
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import org.junit.Assert.*
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.lang.RuntimeException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/09/2019.
 */
@Suppress("PrivatePropertyName")
class GetUserUseCaseImplTest {

    // region constants ----------------------------------------------------------------------------

    private val TEST_CATEGORIES = listOf(
            CategoryEntity("id1", "title1"),
            CategoryEntity("id2", "title2")
    )

    // endregion constants -------------------------------------------------------------------------
    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: GetUserUseCase
    @Mock lateinit var mUserDatabase: UserDatabase
    @Mock lateinit var mCategoryEndPoint: CategoryEndPoint

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = GetUserUseCase(trampoline, trampoline, mCategoryEndPoint, mUserDatabase)
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
    fun fetchUserInfo_validUserAndValidCategory_validUserAndValidData() {
        // Arrange
        validUser()
        validCategory()
        var result: GetUserUseCase.Result? = null
        // Act
        SUT.fetchUserInfo {
            result = it
        }
        // Assert
        assertTrue(result is GetUserUseCase.Result.ValidUser)
        val resultAsValid = result as GetUserUseCase.Result.ValidUser
        assertThat(resultAsValid.user, `is`(TEST_USER_1))
        assertThat(resultAsValid.categories, `is`(TEST_CATEGORIES))
    }

    @Test
    fun fetchUserInfo_validUserAndInvalidCategory_callbackFailed() {
        // Arrange
        validUser()
        invalidCategory()
        var result: GetUserUseCase.Result? = null
        // Act
        SUT.fetchUserInfo {
            result = it
        }
        // Assert
        assertTrue(result is GetUserUseCase.Result.InvalidUser)
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
        `when`(mUserDatabase.getUserFlowable()).thenReturn(Flowable.just(TEST_USER_1))
    }

    private fun invalidUser() {
        `when`(mUserDatabase.getUserFlowable()).thenReturn(Flowable.error(InvalidUserException()))
    }

    private fun validCategory() {
        `when`(mCategoryEndPoint.getAllCategories()).thenReturn(TEST_CATEGORIES)
    }

    private fun invalidCategory() {
        `when`(mCategoryEndPoint.getAllCategories()).thenThrow(RuntimeException())
    }

    // endregion helper methods --------------------------------------------------------------------

}