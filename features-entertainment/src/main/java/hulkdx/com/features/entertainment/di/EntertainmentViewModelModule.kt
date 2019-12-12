package hulkdx.com.features.entertainment.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Binds
import dagger.multibindings.IntoMap
import hulkdx.com.features.common.di.CoreViewModelModule
import hulkdx.com.features.common.di.annotations.ViewModelKey
import hulkdx.com.features.entertainment.viewmodel.EntertainmentViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/12/2019.
 */
@Module(includes = [
    CoreViewModelModule::class
])
internal abstract class EntertainmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EntertainmentViewModel::class)
    abstract fun providesExploreViewModel(exploreViewModel: EntertainmentViewModel): ViewModel

}
    