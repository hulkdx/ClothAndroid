package hulkdx.com.data.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import hulkdx.com.domain.di.ApplicationContext
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 09/11/2018.
 */
@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideRealmConfiguration(@ApplicationContext context: Context): RealmConfiguration {
        Realm.init(context)
        return RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("realm-db")
                .build()
    }

}
