package hulkdx.com.features.category.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hulkdx.com.features.common.di.annotations.FragmentScoped
import hulkdx.com.features.category.view.CategoryFragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/10/2019.
 */
@Module
abstract class CategoryBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        CategoryViewModelModule::class
    ])
    internal abstract fun categoryFragment(): CategoryFragment

}
    