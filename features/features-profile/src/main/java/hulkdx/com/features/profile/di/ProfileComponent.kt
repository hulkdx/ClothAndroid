package hulkdx.com.features.profile.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hulkdx.com.core.android.di.ApplicationComponent
import hulkdx.com.core.android.di.annotations.MainActivityScope
import hulkdx.com.features.profile.view.ProfileFragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */
@MainActivityScope
@Component(modules = [
    ProfileViewModelModule::class
], dependencies = [ApplicationComponent::class])
interface ProfileComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun applicationComponent(applicationComponent: ApplicationComponent): Builder
        fun build(): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)

}
    