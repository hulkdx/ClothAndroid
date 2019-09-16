package hulkdx.com.data.firebase

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import hulkdx.com.data.firebase.database.ClothDatabaseFirebase
import hulkdx.com.data.firebase.database.UserDatabaseFirebase
import hulkdx.com.data.firebase.mapper.ApiModelMapper
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.ArgumentCaptor
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import org.junit.Assert.*
import org.mockito.Mockito.*
import org.hamcrest.CoreMatchers.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 01/09/2019.
 */
@Suppress("PrivatePropertyName")
@RunWith(AndroidJUnit4::class)
class ApiManagerImplAndroidTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!
    @Mock lateinit var mAuth: FirebaseAuth
    @Mock internal lateinit var mFirebaseToResultMapper: FirebaseToResultMapper
    private lateinit var mClothDatabaseFirebase: ClothDatabaseFirebase
    @Mock internal lateinit var mUserDatabase: UserDatabaseFirebase

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: ApiManagerImpl

    @Before
    fun setup() {
        val db = FirebaseDatabase.getInstance()
        val clothDbRef = db.getReference("clothes")
        mClothDatabaseFirebase = ClothDatabaseFirebase(clothDbRef, ApiModelMapper())
        SUT = ApiManagerImpl(mAuth, mFirebaseToResultMapper, mClothDatabaseFirebase, mUserDatabase)
    }

    @Test
    fun add() {
        val AVATAR_IMAGE_1 = ImageEntity(
                size = 1024,
                url = "url1"
        )

        val CLOTH_IMAGE_1 = ImageEntity(
                size = 1025,
                url = "url2"
        )
        val TEST_USER_1  = UserEntity(
                id = "1",
                firstName = "firstName1",
                lastName = "lastName1",
                emailAddress = "email1@gmail.com",
                gender = UserGender.Male,
                image = AVATAR_IMAGE_1
        )

        SUT.addCloth(TEST_USER_1, CLOTH_IMAGE_1, UploadClothUseCase.Params(122F, "EURO"))
    }

    @Test
    fun addCloth() {
        val AVATAR_IMAGE_1 = ImageEntity(
                size = 1024,
                url = "url1"
        )

        val CLOTH_IMAGE_1 = ImageEntity(
                size = 1025,
                url = "url2"
        )
        val TEST_USER_1  = UserEntity(
                id = "1",
                firstName = "firstName1",
                lastName = "lastName1",
                emailAddress = "email1@gmail.com",
                gender = UserGender.Male,
                image = AVATAR_IMAGE_1
        )

        SUT.addCloth(TEST_USER_1, CLOTH_IMAGE_1, UploadClothUseCase.Params(122.3453F, "EURO"))
    }



    @Test
    fun getCloth() {
        val result = SUT.getClothes()

        println(result)
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}