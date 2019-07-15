package hulkdx.com.features.home.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hulkdx.com.core.android.di.ApplicationComponent
import hulkdx.com.core.android.di.MainActivityScope
import hulkdx.com.features.home.ui.HomeFragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
@MainActivityScope
@Component(modules = [
    HomeModule::class
], dependencies = [ApplicationComponent::class])
interface HomeComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun applicationComponent(applicationComponent: ApplicationComponent): Builder
        fun build(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)

}