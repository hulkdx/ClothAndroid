package hulkdx.com.domain.di

import dagger.Binds
import dagger.Module
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCase
import hulkdx.com.domain.interactor.cloth.load.LoadClothUseCaseImpl

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun loginUseCase(impl: LoadClothUseCaseImpl): LoadClothUseCase
}
