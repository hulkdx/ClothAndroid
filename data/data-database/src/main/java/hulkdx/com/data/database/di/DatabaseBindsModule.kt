package hulkdx.com.data.database.di

import dagger.Binds
import dagger.Module
import hulkdx.com.data.database.DatabaseImpl
import hulkdx.com.domain.repository.local.ClothDatabase
import hulkdx.com.domain.repository.local.UserDatabase

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Suppress("unused")
@Module
abstract class DatabaseBindsModule {

    @Binds
    abstract fun provideUserDatabase(impl: DatabaseImpl): UserDatabase

    @Binds
    abstract fun provideClothDatabase(impl: DatabaseImpl): ClothDatabase
}
