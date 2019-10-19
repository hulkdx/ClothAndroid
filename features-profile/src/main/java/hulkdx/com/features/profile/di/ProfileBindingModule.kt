package hulkdx.com.features.profile.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hulkdx.com.features.common.di.annotations.FragmentScoped
import hulkdx.com.features.profile.view.ProfileFragment
import hulkdx.com.features.profile.view.NewClothProfileFragment

@Module
abstract class ProfileBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        ProfileViewModelModule::class
    ])
    internal abstract fun profileFragment(): ProfileFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        ProfileViewModelModule::class
    ])
    internal abstract fun newClothProfileFragment(): NewClothProfileFragment

}
