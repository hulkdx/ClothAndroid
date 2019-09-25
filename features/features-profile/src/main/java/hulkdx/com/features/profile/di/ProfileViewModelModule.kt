package hulkdx.com.features.profile.di

import androidx.lifecycle.ViewModel
import dagger.*
import dagger.multibindings.IntoMap
import hulkdx.com.core.android.di.annotations.ViewModelKey
import hulkdx.com.core.android.viewmodel.CoreViewModel
import hulkdx.com.features.profile.viewmodel.ProfileViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */
@Suppress("unused")
@Module
abstract class ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun providesExploreViewModel(exploreViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoreViewModel::class)
    abstract fun providesCoreViewModel(viewModel: CoreViewModel): ViewModel

}
