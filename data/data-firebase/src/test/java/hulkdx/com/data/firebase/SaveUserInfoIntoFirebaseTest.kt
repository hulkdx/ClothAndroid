package hulkdx.com.data.firebase

import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

import org.mockito.Mockito.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/08/2019.
 */
class SaveUserInfoIntoFirebaseTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: SaveUserInfoIntoFirebase

    @Before
    fun setup() {
        SUT = SaveUserInfoIntoFirebase()
    }

    @Test(expected = SaveUserInfoIntoFirebase.UserNullException::class)
    fun saveUserInfoInFirebase_userNull_throwUserNullException() {
        // Arrange
        val param = RegisterAuthUseCase.Params("", "", "", "")
        // Act
        SUT.saveUserInfoIntoFirebase(param, null)
        // Assert
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}