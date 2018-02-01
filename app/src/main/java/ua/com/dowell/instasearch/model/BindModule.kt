package ua.com.dowell.instasearch.model

import dagger.Binds
import dagger.Module
import ua.com.dowell.instasearch.model.impl.AccountHelperImpl
import ua.com.dowell.instasearch.model.impl.InstagramUrlModelImpl
import ua.com.dowell.instasearch.model.impl.LocationProviderImpl

/**
 * Created by kosty on 23.01.2018.
 */
@Module
interface BindModule {

    @Binds
    fun provideAccountHelper(accountHelperImpl: AccountHelperImpl): AccountHelper

    @Binds
    fun provideInstagramUrlModel(instagramModelImpl: InstagramUrlModelImpl): InstagramUrlModel

    @Binds
    fun provideLocationProvider(locationProviderImpl: LocationProviderImpl): LocationProvider
}