package hulkdx.com.core.android.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hulkdx.com.data.cache.di.CacheModule
import hulkdx.com.data.database.di.DatabaseModule
import hulkdx.com.data.remote.di.RemoteBinderModule
import hulkdx.com.data.remote.di.RemoteModule
import hulkdx.com.domain.di.ApplicationContext
import hulkdx.com.domain.di.RepositoryModule
import hulkdx.com.domain.di.UseCaseModule
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import javax.inject.Singleton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Singleton
@Component(modules = [
    SchedulerModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    DatabaseModule::class,
    CacheModule::class,
    RemoteBinderModule::class,
    RemoteModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        @ApplicationContext
        fun applicationContext(@ApplicationContext context: Context): Builder
        fun build(): ApplicationComponent
    }

    fun loadClothUseCase(): LoadClothUseCase
}


