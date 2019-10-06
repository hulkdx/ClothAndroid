package hulkdx.com.features.explore.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Binds
import dagger.multibindings.IntoMap
import hulkdx.com.core.android.di.CoreViewModelModule
import hulkdx.com.core.android.di.annotations.ViewModelKey
import hulkdx.com.features.explore.viewmodel.ExploreViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 *
 * Using Dagger Multi-bindings to insert Map<Class<? extends ViewModel> into ViewModelFactory.
 */
@Suppress("unused")
@Module(includes = [
    CoreViewModelModule::class
])
internal abstract class ExploreViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExploreViewModel::class)
    abstract fun providesExploreViewModel(exploreViewModel: ExploreViewModel): ViewModel

}
