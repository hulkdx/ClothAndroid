package hulkdx.com.features.home.viewmodel.results

import hulkdx.com.features.home.model.ClothModelViewHolder

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

sealed class ClothesViewModelResults {
    class Success(val clothes: List<ClothModelViewHolder>): ClothesViewModelResults()
    object NetworkError: ClothesViewModelResults()
    object GeneralError: ClothesViewModelResults()
    object AuthError: ClothesViewModelResults()
}
