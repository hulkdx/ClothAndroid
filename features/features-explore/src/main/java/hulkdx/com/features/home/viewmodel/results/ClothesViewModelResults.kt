package hulkdx.com.features.home.viewmodel.results

import hulkdx.com.features.home.model.Cloth

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

sealed class ClothesViewModelResults {
    class Success(val clothes: List<Cloth>): ClothesViewModelResults()
    object NetworkError: ClothesViewModelResults()
    object GeneralError: ClothesViewModelResults()
    object AuthError: ClothesViewModelResults()
}
