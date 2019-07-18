package hulkdx.com.features.home.di

import androidx.lifecycle.ViewModel
import dagger.*
import hulkdx.com.features.home.viewmodel.ExploreViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun providesViewModel(clothViewModel: ExploreViewModel): ViewModel

}