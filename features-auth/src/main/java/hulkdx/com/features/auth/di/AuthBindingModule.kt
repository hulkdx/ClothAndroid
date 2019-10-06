package hulkdx.com.features.auth.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hulkdx.com.core.android.di.annotations.FragmentScoped
import hulkdx.com.features.auth.view.login.LoginFragment
import hulkdx.com.features.auth.view.register.RegisterFragment

@Module
abstract class AuthBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        AuthViewModelModule::class
    ])
    internal abstract fun loginFragment(): LoginFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        AuthViewModelModule::class
    ])
    internal abstract fun registerFragment(): RegisterFragment
}
