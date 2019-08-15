package hulkdx.com.data.firebase

import com.google.firebase.auth.FirebaseAuth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import org.mockito.Mockito.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/08/2019.
 */
@Suppress("RedundantVisibilityModifier")
class ClothApiManagerImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    public var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: ApiManagerImpl
    @Mock lateinit var mAuth: FirebaseAuth

    @Before
    fun setup() {
        SUT = ApiManagerImpl(mAuth)
    }

    @Test
    fun register() {
        // Arrange
        success()
        // Act
        SUT.register("", "", "", "", null)
        // Assert
    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        `when`(mAuth.createUserWithEmailAndPassword(any(), any())).thenReturn(null)
    }

    // endregion helper methods --------------------------------------------------------------------

}