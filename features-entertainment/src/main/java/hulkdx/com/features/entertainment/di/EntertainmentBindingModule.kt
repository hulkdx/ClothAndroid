package hulkdx.com.features.entertainment.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hulkdx.com.features.common.di.annotations.FragmentScoped
import hulkdx.com.features.entertainment.view.EntertainmentFragment

/**
 * Created by Mohammad Jafarzadeh Rezvan on 12/12/2019.
 */
@Module
abstract class EntertainmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        EntertainmentViewModelModule::class
    ])
    internal abstract fun entertainmentFragment(): EntertainmentFragment

}
    