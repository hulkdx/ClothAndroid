package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.model.Cloth


/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
interface LoadClothUseCase {

    fun loadAsync(onComplete: (UseCaseResult<List<Cloth>>) -> Unit)

    fun dispose()
}
