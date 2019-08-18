package hulkdx.com.data.firebase

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.domain.data.remote.RegisterEndPoint
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import org.mockito.Mockito.*
import java.lang.Exception
import java.lang.RuntimeException
import java.util.concurrent.Executor

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/08/2019.
 */
@Suppress("RedundantVisibilityModifier", "PrivatePropertyName")
class ApiManagerImplTest {

    // region constants ----------------------------------------------------------------------------

    private val FIRST_NAME = "first_name"
    private val LAST_NAME = "last_name"

    private val TEST_EXCEPTION = RuntimeException("TEST")

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    public var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: ApiManagerImpl
    @Mock lateinit var mAuth: FirebaseAuth
    @Mock internal lateinit var mFirebaseToResultMapper: FirebaseToResultMapper
    @Mock internal lateinit var mSaveUserInfoIntoFirebase: SaveUserInfoIntoFirebase

    @Before
    fun setup() {
        SUT = ApiManagerImpl(mAuth, mFirebaseToResultMapper, mSaveUserInfoIntoFirebase)
    }

    @Test
    fun register_success_callSaveUserInfoIntoFirebase() {
        // Arrange
        success()
        // Act
        SUT.register("", "", FIRST_NAME, LAST_NAME, null)
        // Assert
        verify(mSaveUserInfoIntoFirebase).start(FIRST_NAME, LAST_NAME)
    }

    @Test
    fun register_success_resultIsSuccess() {
        // Arrange
        success()
        // Act
        val result = SUT.register("", "", FIRST_NAME, LAST_NAME, null)
        // Assert
        assertTrue(result is RegisterEndPoint.Result.Success)
    }

    @Test
    fun register_success_callMapperSuccess() {
        // Arrange
        success()
        // Act
        SUT.register("", "", FIRST_NAME, LAST_NAME, null)
        // Assert
        verify(mFirebaseToResultMapper).mapSuccess(anyKotlin())
    }

    @Test
    fun register_exception_callMapperError() {
        // Arrange
        exception()
        // Act
        SUT.register("", "", FIRST_NAME, LAST_NAME, null)
        // Assert
        verify(mFirebaseToResultMapper).mapError(TEST_EXCEPTION)
    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        val result = object: Task<AuthResult>() {
            override fun isComplete(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getException(): Exception? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getResult(): AuthResult? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isSuccessful(): Boolean {
                return true
            }

            override fun isCanceled(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnCompleteListener(p0: OnCompleteListener<AuthResult>): Task<AuthResult> {
                p0.onComplete(this)
                return this
            }
        }
        `when`(mAuth.createUserWithEmailAndPassword(any(), any())).thenReturn(result)
        `when`(mFirebaseToResultMapper.mapSuccess(anyKotlin())).thenReturn(RegisterEndPoint.Result.Success())
    }

    private fun exception() {
        val result = object: Task<AuthResult>() {
            override fun isComplete(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getException(): Exception? {
                return TEST_EXCEPTION
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getResult(): AuthResult? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isSuccessful(): Boolean {
                return false
            }

            override fun isCanceled(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun addOnCompleteListener(p0: OnCompleteListener<AuthResult>): Task<AuthResult> {
                p0.onComplete(this)
                return this
            }
        }
        `when`(mAuth.createUserWithEmailAndPassword(any(), any())).thenReturn(result)
        `when`(mFirebaseToResultMapper.mapError(anyKotlin())).thenReturn(RegisterEndPoint.Result.GeneralError(TEST_EXCEPTION))
    }

    // endregion helper methods --------------------------------------------------------------------

}