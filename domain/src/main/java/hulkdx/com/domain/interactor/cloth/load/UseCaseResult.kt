package hulkdx.com.domain.interactor.cloth.load

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

const val STATUS_SUCCESS        = 0
const val STATUS_NETWORK_ERROR  = 1
const val STATUS_GENERAL_ERROR  = 2

class UseCaseResult<T>(
        private val status: Int,
        private val data: T? = null
)
