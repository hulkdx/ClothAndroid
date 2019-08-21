package hulkdx.com.data.firebase

import android.net.Uri
import android.os.Parcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.gms.internal.firebase_auth.zzey
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit

import org.mockito.Mockito.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/08/2019.
 */
@RunWith(AndroidJUnit4::class)
class FirebaseDatabaseAndroidTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: SaveUserInfoIntoFirebase

    @Before
    fun setup() {
        val userReference = FirebaseDatabase.getInstance().getReference("user")
        SUT = SaveUserInfoIntoFirebase(userReference)
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}