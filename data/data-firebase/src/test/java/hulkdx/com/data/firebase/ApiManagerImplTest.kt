package hulkdx.com.data.firebase

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nhaarman.mockitokotlin2.argumentCaptor
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
        val param = RegisterAuthUseCase.Params("", "", FIRST_NAME, LAST_NAME)
        val c = argumentCaptor<RegisterAuthUseCase.Params>()
        success()
        // Act
        SUT.register(param)
        // Assert
        verify(mSaveUserInfoIntoFirebase).saveUserInfoIntoFirebase(c.capture(), anyKotlin())
        assertThat(c.firstValue, `is`(param))
    }

    @Test
    fun register_success_resultIsSuccess() {
        // Arrange
        success()
        // Act
        val result = SUT.register(
                RegisterAuthUseCase.Params("", "", FIRST_NAME, LAST_NAME))
        // Assert
        assertTrue(result is RegisterAuthUseCase.Result.Success)
    }

    @Test
    fun register_success_callMapperSuccess() {
        // Arrange
        success()
        // Act
        SUT.register(RegisterAuthUseCase.Params("", "", FIRST_NAME, LAST_NAME))
        // Assert
        verify(mFirebaseToResultMapper).mapSuccess(anyKotlin())
    }

    @Test
    fun register_exception_callMapperError() {
        // Arrange
        exception()
        // Act
        SUT.register(RegisterAuthUseCase.Params("", "", FIRST_NAME, LAST_NAME))
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
        `when`(mFirebaseToResultMapper.mapSuccess(anyKotlin())).thenReturn(RegisterAuthUseCase.Result.Success())
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
        `when`(mFirebaseToResultMapper.mapError(anyKotlin())).thenReturn(RegisterAuthUseCase.Result.GeneralError(TEST_EXCEPTION))
    }

    // endregion helper methods --------------------------------------------------------------------

}