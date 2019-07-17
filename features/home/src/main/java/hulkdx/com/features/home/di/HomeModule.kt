package hulkdx.com.features.home.di

import androidx.lifecycle.ViewModel
import dagger.*
import hulkdx.com.features.home.viewmodel.HomeViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
@Module
object HomeModule {
    @JvmStatic
    @Provides
    fun providesViewModel(clothViewModel: HomeViewModel): ViewModel = clothViewModel
}