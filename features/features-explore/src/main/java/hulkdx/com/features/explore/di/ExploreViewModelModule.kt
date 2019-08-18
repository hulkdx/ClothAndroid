package hulkdx.com.features.explore.di

import androidx.lifecycle.ViewModel
import dagger.*
import dagger.multibindings.IntoMap
import hulkdx.com.core.android.di.annotations.ViewModelKey
import hulkdx.com.core.android.viewmodel.AuthCommonViewModel
import hulkdx.com.features.explore.viewmodel.ExploreViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 *
 * Using Dagger Multi-bindings to insert Map<Class<? extends ViewModel> into ViewModelFactory.
 */
@Module
abstract class ExploreViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExploreViewModel::class)
    abstract fun providesExploreViewModel(exploreViewModel: ExploreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthCommonViewModel::class)
    abstract fun providesAuthViewModel(authViewModel: AuthCommonViewModel): ViewModel

}
