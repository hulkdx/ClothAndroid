package hulkdx.com.domain.di

import dagger.Binds
import dagger.Module
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCaseImpl
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCaseImpl
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCaseImpl

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun loadClothUseCase(impl: LoadClothUseCaseImpl): LoadClothUseCase

    @Binds
    abstract fun registerAuthUseCase(impl: RegisterAuthUseCaseImpl): RegisterAuthUseCase

    @Binds
    abstract fun uploadClothUseCase(impl: UploadClothUseCaseImpl): UploadClothUseCase

}
