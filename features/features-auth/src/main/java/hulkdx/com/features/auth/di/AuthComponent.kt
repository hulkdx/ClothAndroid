package hulkdx.com.features.auth.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.di.ApplicationComponent
import hulkdx.com.core.android.di.annotations.MainActivityScope
import hulkdx.com.features.auth.view.login.LoginFragment
import hulkdx.com.features.auth.view.register.RegisterFragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
@MainActivityScope
@Component(modules = [
    AuthViewModelModule::class
], dependencies = [ApplicationComponent::class])
interface AuthComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun applicationComponent(applicationComponent: ApplicationComponent): Builder
        fun build(): AuthComponent
    }

    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)

}

fun getAuthComponent(context: Context): AuthComponent {
    return DaggerAuthComponent.builder()
            .context(context)
            .applicationComponent(applicationComponent(context))
            .build()
}
