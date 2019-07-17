package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.TEST_CLOTHES
import hulkdx.com.domain.data.remote.ClothApiManager
import hulkdx.com.domain.model.Cloth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

import io.reactivex.schedulers.Schedulers

import org.junit.Assert.*
import org.mockito.Mockito.*
import org.hamcrest.CoreMatchers.*
import java.io.IOException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
@Suppress("HasPlatformType")
class LoadClothUseCaseImplTest {

    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()
    @Mock lateinit var mClothApiManager: ClothApiManager

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: LoadClothUseCaseImpl

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = LoadClothUseCaseImpl(trampoline, trampoline, mClothApiManager)
    }

    @Test
    fun loadAsync_callApi() {
        SUT.loadAsync {}
        verify(mClothApiManager).getCloths()
    }

    @Test
    fun loadAsync_success_resultSuccess() {
        success()
        var result: LoadClothesCallBack<List<Cloth>>? = null
        SUT.loadAsync {
            result = it
        }
        assertThat(result?.status, `is`(STATUS_SUCCESS))
        assertThat(result?.data, `is`(TEST_CLOTHES))
    }

    @Test
    fun loadAsync_ioException_networkError() {
        ioException()
        var result: LoadClothesCallBack<List<Cloth>>? = null
        SUT.loadAsync {
            result = it
        }
        assertThat(result?.status, `is`(STATUS_NETWORK_ERROR))
    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        `when`(mClothApiManager.getCloths()).thenReturn(TEST_CLOTHES)
    }

    private fun ioException() {
        `when`(mClothApiManager.getCloths()).thenThrow(IOException(""))
    }

    // endregion helper methods --------------------------------------------------------------------

}