package hulkdx.com.data.remote

import hulkdx.com.domain.*
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
import org.mockito.ArgumentMatchers.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */
class ClothApiManagerImplTest {

    // region constants ----------------------------------------------------------------------------

    private val JsonSuccess = "[\n" +
            "    {\n" +
            "        \"id\": ${TEST_CLOTH_1.id},\n" +
            "        \"image_url\": \"${TEST_CLOTH_1.imageUrl}\",\n" +
            "        \"price\": ${TEST_CLOTH_1.price},\n" +
            "        \"user\": {\n" +
            "            \"id\": ${TEST_USER_1.id},\n" +
            "            \"first_name\": \"${TEST_USER_1.firstName}\",\n" +
            "            \"last_name\": \"${TEST_USER_1.lastName}\",\n" +
            "            \"email_address\": \"${TEST_USER_1.emailAddress}\",\n" +
            "            \"avatar_image_url\": \"${TEST_USER_1.avatarImageUrl}\"\n" +
            "        }\n" +
            "    }\n" +
            "]"

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()!!

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: ClothApiManagerImpl

    @Before
    fun setup() {
        SUT = ClothApiManagerImpl()
    }

//    @Test
//    fun getCloths_() {
//        SUT.getCloths()
//    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}