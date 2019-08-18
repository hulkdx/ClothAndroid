package hulkdx.com.data.firebase.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import hulkdx.com.data.firebase.ApiManagerImpl
import hulkdx.com.domain.data.remote.GetClothesEndPoint

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@Module
object RemoteModule {

    @Provides
    @JvmStatic
    internal fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}
