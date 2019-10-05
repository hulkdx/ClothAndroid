package hulkdx.com.core.android.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hulkdx.com.core.android.navigation.NavigationManagerWrapper
import hulkdx.com.data.cache.di.CacheModule
import hulkdx.com.data.database.di.DatabaseBindsModule
import hulkdx.com.data.database.di.DatabaseModule
import hulkdx.com.data.firebase.di.RemoteBindsModule
import hulkdx.com.data.firebase.di.RemoteModule
import hulkdx.com.domain.repository.remote.RegisterEndPoint
import hulkdx.com.domain.di.ApplicationContext
import hulkdx.com.domain.di.UseCaseModule
import hulkdx.com.domain.interactor.auth.login.LoginAuthUseCase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import javax.inject.Singleton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Singleton
@Component(modules = [
    SchedulerModule::class,
    UseCaseModule::class,
    DatabaseBindsModule::class,
    DatabaseModule::class,
    CacheModule::class,
    RemoteModule::class,
    RemoteBindsModule::class
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
    fun registerAuthUseCase(): RegisterAuthUseCase
    fun uploadClothUseCase(): UploadClothUseCase
    fun getUserUseCase(): GetUserUseCase
    fun navigationManagerWrapper(): NavigationManagerWrapper
    fun registerEndPoint(): RegisterEndPoint
    fun loginAuthUseCase(): LoginAuthUseCase

}


