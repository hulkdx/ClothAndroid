package hulkdx.com.data.firebase.mapper

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import hulkdx.com.domain.data.remote.RegisterEndPoint
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

import org.junit.Assert.*
import org.mockito.Mock

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
class FirebaseToResultMapperTest {

    // region constants ----------------------------------------------------------------------------


    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!
    @Mock lateinit var mFirebaseAuthWeakPasswordException: FirebaseAuthWeakPasswordException
    @Mock lateinit var mFirebaseAuthInvalidCredentialsException: FirebaseAuthInvalidCredentialsException
    @Mock lateinit var mFirebaseAuthUserCollisionException: FirebaseAuthUserCollisionException


    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: FirebaseToResultMapper

    @Before
    fun setup() {
        SUT = FirebaseToResultMapper()
    }

    @Test
    fun mapError_FirebaseAuthWeakPasswordException_returnWeakPassword() {
        // Arrange
        // Act
        val result = SUT.mapError(mFirebaseAuthWeakPasswordException)
        // Assert
        assertTrue(result is Result.WeakPassword)
    }

    @Test
    fun mapError_FirebaseAuthInvalidCredentialsException_returnInvalidEmailAddress() {
        // Arrange
        // Act
        val result = SUT.mapError(mFirebaseAuthInvalidCredentialsException)
        // Assert
        assertTrue(result is Result.InvalidEmailAddress)
    }

    @Test
    fun mapError_FirebaseAuthUserCollisionException_returnAccountExists() {
        // Arrange
        // Act
        val result = SUT.mapError(mFirebaseAuthUserCollisionException)
        // Assert
        assertTrue(result is Result.AccountExists)
    }

    // region helper methods -----------------------------------------------------------------------

    // endregion helper methods --------------------------------------------------------------------

}