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
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import hulkdx.com.data.firebase.database.ClothDatabaseFirebase
import hulkdx.com.data.firebase.database.UserDatabaseFirebase
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
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

    private val TEST_IMAGE_1 = ImageEntity(
            size = 1025,
            url = "url2"
    )

    private val TEST_USER_1  = UserEntity(
            id = "1",
            firstName = "firstName1",
            lastName = "lastName1",
            emailAddress = "email1@gmail.com",
            gender = UserGender.Male,
            image = TEST_IMAGE_1
    )

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    public var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: ApiManagerImpl
    @Mock lateinit var mAuth: FirebaseAuth
    @Mock internal lateinit var mFirebaseToResultMapper: FirebaseToResultMapper
    @Mock internal lateinit var mUserDatabaseFirebase: UserDatabaseFirebase
    @Mock internal lateinit var mClothDatabaseFirebase: ClothDatabaseFirebase

    @Before
    fun setup() {
        SUT = ApiManagerImpl(mAuth, mFirebaseToResultMapper, mClothDatabaseFirebase, mUserDatabaseFirebase)
    }

    // endregion register --------------------------------------------------------------------------

    @Test
    fun register_createUserSuccessANDSaveUserSuccess_callSaveUserInfoIntoFirebase() {
        // Arrange
        val c = argumentCaptor<RegisterAuthUseCase.Params>()
        createUserWithEmailAndPasswordSuccess()
        saveUserSuccess()
        // Act
        SUT.register(TEST_REGISTER_PARAM)
        // Assert
        verify(mUserDatabaseFirebase).register(c.capture(), anyKotlin(), anyKotlin())
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
        verify(mFirebaseToResultMapper).mapErrorRegister(TEST_EXCEPTION)
    }

    @Test
    fun register_createUserWithEmailAndPasswordException_notSaveUserInfoIntoFirebase() {
        // Arrange
        createUserWithEmailAndPasswordException()
        // Act
        SUT.register(TEST_REGISTER_PARAM)
        // Assert
        verify(mUserDatabaseFirebase, never()).register(anyKotlin(), anyKotlin(), anyKotlin())
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

    // endregion register --------------------------------------------------------------------------
    // region Login --------------------------------------------------------------------------------

    @Test
    fun login_() {
        val m = mapOf<String, Any>("test" to 1, "tets2" to 12)
        val g = Gson()
        val k = g.toJsonTree(m)
        val s = g.fromJson(k, ZZ::class.java)
        println(s)
    }

    data class ZZ(val test: Int, val tets2: String)

    // endregion Login -----------------------------------------------------------------------------
    // region addCloth -----------------------------------------------------------------------------

    @Test(expected = AuthException::class)
    fun addCloth_userNull_AuthException() {
        // Arrange
        userNull()
        // Act
        SUT.addCloth(TEST_USER_1, TEST_IMAGE_1, UploadClothUseCase.Params(0f, ""))
        // Assert
    }

    // endregion addCloth --------------------------------------------------------------------------
    // region getClothes ---------------------------------------------------------------------------

    @Test(expected = AuthException::class)
    fun getClothes_userNull_AuthException() {
        // Arrange
        userNull()
        // Act
        SUT.getClothes()
        // Assert
    }

    // endregion getClothes ------------------------------------------------------------------------
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

        userValid()
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
        `when`(mFirebaseToResultMapper.mapErrorRegister(anyKotlin())).thenReturn(RegisterAuthUseCase.Result.GeneralError(TEST_EXCEPTION))
    }

    private fun saveUserSuccess() {
        val dbRef = mock(DatabaseReference::class.java)

        doAnswer { invocation ->
            invocation.getArgument<DatabaseReference.CompletionListener>(2)
                    .onComplete(null, dbRef)
            null
        }.`when`(mUserDatabaseFirebase).register(anyKotlin(), anyKotlin(), anyKotlin())
    }

    private fun saveUserFailed() {
        val dbRef = mock(DatabaseReference::class.java)

        doAnswer { invocation ->
            invocation.getArgument<DatabaseReference.CompletionListener>(2)
                    .onComplete(DatabaseError.fromCode(-1), dbRef)
            null
        }.`when`(mUserDatabaseFirebase).register(anyKotlin(), anyKotlin(), anyKotlin())

        val user = mock(FirebaseUser::class.java)
        `when`(mAuth.currentUser).thenReturn(user)
    }

    private fun userValid() {
        val currentUserMock = mock(FirebaseUser::class.java)
        `when`(mAuth.currentUser).thenReturn(currentUserMock)
        `when`(currentUserMock.uid).thenReturn("UID")
    }

    private fun userNull() {
        `when`(mAuth.currentUser).thenReturn(null)
    }

    // endregion helper methods --------------------------------------------------------------------

}