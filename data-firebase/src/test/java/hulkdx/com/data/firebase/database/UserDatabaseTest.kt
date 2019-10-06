package hulkdx.com.data.firebase.database

import android.net.Uri
import android.os.Parcel
import com.google.android.gms.internal.firebase_auth.zzex
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.anyKotlin
import hulkdx.com.data.firebase.mapper.ApiModelMapper
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import org.mockito.Mockito.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/08/2019.
 */
@Suppress("PrivatePropertyName", "UNCHECKED_CAST")
class UserDatabaseTest {
    // region constants ----------------------------------------------------------------------------

    private val TEST_PARAM = RegisterAuthUseCase.Params("", "", "", "", UserGender.Male)

    private val TEST_VALID_FIREBASE_USER = object: FirebaseUser() {
        override fun zze(): zzex {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zza(p0: zzex) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun zzg(): String {
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

    private lateinit var SUT: UserDatabaseFirebase
    @Mock lateinit var mUserDatabase: DatabaseReference
    @Mock internal lateinit var mApiModelMapper: ApiModelMapper

    @Before
    fun setup() {
        SUT = UserDatabaseFirebase(mUserDatabase, mApiModelMapper)
    }

    @Test
    fun saveUserInfoInFirebase_userNotNull_should_callDatabaseReference() {
        // Arrange
        val mockChild = mock(DatabaseReference::class.java)
        `when`(mUserDatabase.child(anyKotlin())).thenReturn(mockChild)
        // Act
        SUT.register(TEST_PARAM, TEST_VALID_FIREBASE_USER, DatabaseReference.CompletionListener { _, _ -> })
        // Assert
        verify(mUserDatabase).child(anyKotlin())
        verify(mockChild).setValue(anyKotlin(), anyKotlin())
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}