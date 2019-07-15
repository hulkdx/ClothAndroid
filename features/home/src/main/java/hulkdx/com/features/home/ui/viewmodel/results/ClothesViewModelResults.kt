package hulkdx.com.features.home.ui.viewmodel.results

import hulkdx.com.features.home.ui.adapter.ClothViewHolderModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

sealed class ClothesViewModelResults {
    class Success(val clothes: ClothViewHolderModel): ClothesViewModelResults()
    object Error: ClothesViewModelResults()
}
