package hulkdx.com.data.firebase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import javax.inject.Named

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

    @Provides
    @JvmStatic
    internal fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Named("USER")
    @JvmStatic
    internal fun provideUserDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference("users")
    }

    @Provides
    @Named("CLOTH")
    @JvmStatic
    internal fun provideClothDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference("clothes")
    }

    @Provides
    @JvmStatic
    internal fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @JvmStatic
    internal fun provideStorageReference(firebaseStorage: FirebaseStorage): StorageReference {
        return firebaseStorage.getReference("images")
    }
}
