package hulkdx.com.features.category.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Binds
import dagger.multibindings.IntoMap
import hulkdx.com.features.common.di.CoreViewModelModule
import hulkdx.com.features.common.di.annotations.ViewModelKey
import hulkdx.com.features.category.viewmodel.CategoryViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/10/2019.
 */
@Module(includes = [
    CoreViewModelModule::class
])
internal abstract class CategoryViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun providesExploreViewModel(exploreViewModel: CategoryViewModel): ViewModel

}
    