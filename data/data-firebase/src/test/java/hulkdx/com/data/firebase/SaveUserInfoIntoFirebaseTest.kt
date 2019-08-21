package hulkdx.com.data.firebase

import android.net.Uri
import android.os.Parcel
import com.google.android.gms.internal.firebase_auth.zzey
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

import org.mockito.Mockito.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/08/2019.
 */
@Suppress("PrivatePropertyName", "UNCHECKED_CAST")
class SaveUserInfoIntoFirebaseTest {
    // region constants ----------------------------------------------------------------------------

    private val TEST_PARAM = RegisterAuthUseCase.Params("", "", "", "", UserGender.Male)

    private val TEST_VALID_FIREBASE_USER = object: FirebaseUser() {
        override fun zzg(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zze(): zzey {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getEmail(): String? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zzc(): FirebaseApp {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zza(): MutableList<String> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zza(p0: MutableList<out UserInfo>): FirebaseUser {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zza(p0: zzey) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getProviderData(): MutableList<out UserInfo> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getMetadata(): FirebaseUserMetadata? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isAnonymous(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getPhoneNumber(): String? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getUid(): String {
            return "UUID"
        }

        override fun zzh(): zzz {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isEmailVerified(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zzf(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zzd(): String? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zzb(): FirebaseUser {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zzb(p0: MutableList<zzy>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getDisplayName(): String? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getPhotoUrl(): Uri? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getProviderId(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: SaveUserInfoIntoFirebase
    @Mock lateinit var mUserDatabase: DatabaseReference

    @Before
    fun setup() {
        SUT = SaveUserInfoIntoFirebase(mUserDatabase)
    }

    @Test(expected = SaveUserInfoIntoFirebase.UserNullException::class)
    fun saveUserInfoInFirebase_userNull_throwUserNullException() {
        // Arrange
        // Act
        SUT.saveUserInfoIntoFirebase(TEST_PARAM, null)
        // Assert
        // assert is done in expected = SaveUserInfoIntoFirebase.UserNullException::class
    }

    @Test
    fun saveUserInfoInFirebase_userNotNull_should_callDatabaseReference() {
        // Arrange
        val mockPush = mock(DatabaseReference::class.java)
        val mockTask: Task<Void> = mock(Task::class.java) as Task<Void>
        `when`(mUserDatabase.push()).thenReturn(mockPush)
        `when`(mockPush.setValue(anyKotlin())).thenReturn(mockTask)
        // Act
        SUT.saveUserInfoIntoFirebase(TEST_PARAM, TEST_VALID_FIREBASE_USER)
        // Assert
        verify(mUserDatabase).push()
        verify(mockPush).setValue(anyKotlin())
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}