package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.model.Cloth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.ArgumentCaptor
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

import org.junit.Assert.*
import org.mockito.Mockito.*
import org.hamcrest.CoreMatchers.*
import org.mockito.ArgumentMatchers.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
class LoadClothUseCaseImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @get:Rule
    var mMockitoJUnit = MockitoJUnit.rule()

    // endregion helper fields ---------------------------------------------------------------------

    private lateinit var SUT: LoadClothUseCaseImpl

    @Before
    fun setup() {
        val trampoline = Schedulers.trampoline()
        SUT = LoadClothUseCaseImpl(trampoline, trampoline)
    }

    @Test
    fun loadAsync_() {
        var result: UseCaseResult<List<Cloth>>? = null
        SUT.loadAsync {
            result = it
        }

    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}