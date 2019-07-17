package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.model.Cloth


/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
interface LoadClothUseCase {

    fun loadAsync(onSuccess:      (List<Cloth>) -> Unit,
                  onGeneralError: (Throwable)   -> Unit,
                  onNetworkError: (Throwable)   -> Unit,
                  onAuthError:    (Throwable)   -> Unit
    )

    fun dispose()
}
