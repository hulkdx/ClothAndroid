package hulkdx.com.features.auth.di

import androidx.lifecycle.ViewModel
import dagger.*
import dagger.multibindings.IntoMap
import hulkdx.com.core.android.di.annotations.ViewModelKey
import hulkdx.com.core.android.viewmodel.AuthCommonViewModel
import hulkdx.com.features.auth.viewmodel.AuthViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 *
 * Using Dagger Multi-bindings to insert Map<Class<? extends ViewModel> into ViewModelFactory.
 */
@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun providesExploreViewModel(exploreViewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthCommonViewModel::class)
    abstract fun providesAuthViewModel(authViewModel: AuthCommonViewModel): ViewModel

}
