package hulkdx.com.data.firebase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import hulkdx.com.data.firebase.ApiManagerImpl
import hulkdx.com.data.firebase.database.ClothDatabaseFirebase
import hulkdx.com.data.firebase.database.UserDatabaseFirebase
import hulkdx.com.data.firebase.mapper.ApiModelMapper
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.data.firebase.storage.upload.FileUploaderImpl
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
    internal fun provideUserDatabaseReference(): DatabaseReference {
        return provideFirebaseDatabase().getReference("users")
    }

    @Provides
    @Named("CLOTH")
    @JvmStatic
    internal fun provideClothDatabaseReference(): DatabaseReference {
        return provideFirebaseDatabase().getReference("clothes")
    }

    @Provides
    @JvmStatic
    internal fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @JvmStatic
    internal fun provideStorageReference(): StorageReference {
        return provideFirebaseStorage().getReference("images")
    }

    @Provides
    @JvmStatic
    internal fun provideApiManagerImpl(mFirebaseToResultMapper: FirebaseToResultMapper,
                                       mClothDatabaseFirebase: ClothDatabaseFirebase,
                                       mUserDatabase: UserDatabaseFirebase): ApiManagerImpl {
        return ApiManagerImpl(
                provideFirebaseAuth(),
                mFirebaseToResultMapper,
                mClothDatabaseFirebase,
                mUserDatabase
        )
    }

    @Provides
    @JvmStatic
    internal fun provideClothDatabaseFirebase(apiModelMapper: ApiModelMapper): ClothDatabaseFirebase {
        return ClothDatabaseFirebase(
                provideClothDatabaseReference(),
                apiModelMapper
        )
    }

    @Provides
    @JvmStatic
    internal fun provideUserDatabaseFirebase(apiModelMapper: ApiModelMapper): UserDatabaseFirebase {
        return UserDatabaseFirebase(
                provideUserDatabaseReference(),
                apiModelMapper
        )
    }

    @Provides
    @JvmStatic
    internal fun provideFileUploaderImpl(): FileUploaderImpl {
        return FileUploaderImpl(
                provideStorageReference()
        )
    }
}
