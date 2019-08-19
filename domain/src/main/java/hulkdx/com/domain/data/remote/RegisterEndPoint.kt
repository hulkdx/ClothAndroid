package hulkdx.com.domain.data.remote

import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/08/2019.
 */
interface RegisterEndPoint {

    fun register(param: RegisterAuthUseCase.Params): RegisterAuthUseCase.Result

}