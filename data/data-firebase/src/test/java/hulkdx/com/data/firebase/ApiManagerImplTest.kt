package hulkdx.com.data.firebase

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.nhaarman.mockitokotlin2.argumentCaptor
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
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
    private val EMAIL = "email@google.com"
    private val PASSWORD = "123456"

    private val TEST_REGISTER_PARAM = RegisterAuthUseCase.Params(
            EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, UserGender.Unknown)

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
    fun register_createUserSuccessANDSaveUserSuccess_callSaveUserInfoIntoFirebase() {
        // Arrange
        val c = argumentCaptor<RegisterAuthUseCase.Params>()
        createUserWithEmailAndPasswordSuccess()
        saveUserSuccess()
        // Act
        SUT.register(TEST_REGISTER_PARAM)
        // Assert
        verify(mSaveUserInfoIntoFirebase).saveUserInfo(c.capture(), anyKotlin(), anyKotlin())
        assertThat(c.firstValue, `is`(TEST_REGISTER_PARAM))
    }

    @Test
    fun register_createUserSuccessANDSaveUserSuccess_resultIsSuccess() {
        // Arrange
        createUserWithEmailAndPasswordSuccess()
        saveUserSuccess()
        // Act
        val result = SUT.register(TEST_REGISTER_PARAM)
        // Assert
        assertTrue(result is RegisterAuthUseCase.Result.Success)
    }

    @Test
    fun register_createUserWithEmailAndPasswordException_callMapperError() {
        // Arrange
        createUserWithEmailAndPasswordException()
        // Act
        SUT.register(TEST_REGISTER_PARAM)
        // Assert
        verify(mFirebaseToResultMapper).mapError(TEST_EXCEPTION)
    }

    @Test
    fun register_createUserWithEmailAndPasswordException_notSaveUserInfoIntoFirebase() {
        // Arrange
        createUserWithEmailAndPasswordException()
        // Act
        SUT.register(TEST_REGISTER_PARAM)
        // Assert
        verify(mSaveUserInfoIntoFirebase, never()).saveUserInfo(anyKotlin(), anyKotlin(), anyKotlin())
    }

    @Test
    fun register_createUserSuccessANDSaveUserFailed_resultGeneralError() {
        // Arrange
        createUserWithEmailAndPasswordSuccess()
        saveUserFailed()
        // Act
        val result = SUT.register(TEST_REGISTER_PARAM)
        // Assert
        assertTrue(result is RegisterAuthUseCase.Result.GeneralError)
    }

    @Test
    fun register_createUserSuccessANDSaveUserFailed_deleteFirebaseAuth() {
        // Arrange
        createUserWithEmailAndPasswordSuccess()
        saveUserFailed()
        // Act
        SUT.register(TEST_REGISTER_PARAM)
        // Assert
        verify(mAuth.currentUser!!).delete()
    }

    // region helper methods -----------------------------------------------------------------------

    private fun createUserWithEmailAndPasswordSuccess() {
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
    }

    private fun createUserWithEmailAndPasswordException() {
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

    private fun saveUserSuccess() {
        val dbRef = mock(DatabaseReference::class.java)

        doAnswer { invocation ->
            invocation.getArgument<DatabaseReference.CompletionListener>(2)
                    .onComplete(null, dbRef)
            null
        }.`when`(mSaveUserInfoIntoFirebase).saveUserInfo(anyKotlin(), anyKotlin(), anyKotlin())
    }

    private fun saveUserFailed() {
        val dbRef = mock(DatabaseReference::class.java)

        doAnswer { invocation ->
            invocation.getArgument<DatabaseReference.CompletionListener>(2)
                    .onComplete(DatabaseError.fromCode(-1), dbRef)
            null
        }.`when`(mSaveUserInfoIntoFirebase).saveUserInfo(anyKotlin(), anyKotlin(), anyKotlin())

        val user = mock(FirebaseUser::class.java)
        `when`(mAuth.currentUser).thenReturn(user)
    }

    // endregion helper methods --------------------------------------------------------------------

}