package hulkdx.com.data.firebase.di

import dagger.Binds
import dagger.Module
import hulkdx.com.data.firebase.ApiManagerImpl
import hulkdx.com.domain.data.remote.ApiManager

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@Module
abstract class RemoteBindsModule {

    @Binds
    internal abstract fun provideClothApiManager(clothApiManagerImpl: ApiManagerImpl): ApiManager

}
