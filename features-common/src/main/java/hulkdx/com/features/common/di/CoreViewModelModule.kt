package hulkdx.com.features.common.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import hulkdx.com.features.common.di.annotations.ViewModelKey
import hulkdx.com.features.common.viewmodel.CoreViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Suppress("unused")
@Module
abstract class CoreViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoreViewModel::class)
    abstract fun providesCoreViewModel(viewModel: CoreViewModel): ViewModel

}
