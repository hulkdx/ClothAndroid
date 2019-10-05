package hulkdx.com.domain.repository.remote

import hulkdx.com.domain.interactor.auth.login.LoginAuthUseCase


/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/08/2019.
 */
interface LoginEndPoint {

    fun login(username: String, password: String): LoginAuthUseCase.Result

}
