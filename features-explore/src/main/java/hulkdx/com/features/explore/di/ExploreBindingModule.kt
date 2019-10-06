package hulkdx.com.features.explore.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hulkdx.com.core.android.di.annotations.FragmentScoped
import hulkdx.com.features.explore.view.detail.ExploreDetailFragment
import hulkdx.com.features.explore.view.list.ExploreListFragment

@Module
abstract class ExploreBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        ExploreViewModelModule::class
    ])
    internal abstract fun exploreListFragment(): ExploreListFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        ExploreViewModelModule::class
    ])
    internal abstract fun exploreDetailFragment(): ExploreDetailFragment
}
