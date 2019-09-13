package hulkdx.com.data.firebase.di

import dagger.Binds
import dagger.Module
import hulkdx.com.data.firebase.ApiManagerImpl
import hulkdx.com.data.firebase.storage.upload.FileUploaderImpl
import hulkdx.com.domain.repository.remote.AddClothEndPoint
import hulkdx.com.domain.repository.remote.FileUploader
import hulkdx.com.domain.repository.remote.GetClothesEndPoint
import hulkdx.com.domain.repository.remote.RegisterEndPoint

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@Suppress("unused")
@Module
abstract class RemoteBindsModule {

    @Binds
    internal abstract fun provideGetClothesEndPoint(impl: ApiManagerImpl): GetClothesEndPoint

    @Binds
    internal abstract fun provideRegisterEndPoint(impl: ApiManagerImpl): RegisterEndPoint

    @Binds
    internal abstract fun provideFileUploader(impl: FileUploaderImpl): FileUploader

    @Binds
    internal abstract fun provideAddClothEndPoint(impl: ApiManagerImpl): AddClothEndPoint

}
