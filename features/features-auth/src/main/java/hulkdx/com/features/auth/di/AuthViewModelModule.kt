package hulkdx.com.features.auth.di

import androidx.lifecycle.ViewModel
import dagger.*
import dagger.multibindings.IntoMap
import hulkdx.com.core.android.di.annotations.ViewModelKey

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
    abstract fun providesExploreViewModel(exploreViewModel: hulkdx.com.features.auth.viewmodel.AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun providesAuthViewModel(authViewModel: AuthViewModel): ViewModel

}
