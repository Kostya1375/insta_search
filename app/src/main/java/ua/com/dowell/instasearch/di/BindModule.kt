package ua.com.dowell.instasearch.di

import dagger.Binds
import dagger.Module
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.InstagramModel
import ua.com.dowell.instasearch.model.impl.AccountHelperImpl
import ua.com.dowell.instasearch.model.impl.InstagramModelImpl

/**
 * Created by kosty on 23.01.2018.
 */
@Module
interface BindModule {

    @Binds
    fun provideAccountHelper(accountHelperImpl: AccountHelperImpl): AccountHelper

    @Binds
    fun provideInstagramModel(instagramModelImpl: InstagramModelImpl): InstagramModel
}