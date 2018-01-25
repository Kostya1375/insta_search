package ua.com.dowell.instasearch.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.com.dowell.instasearch.MainApplication
import javax.inject.Singleton

/**
 * Created by kosty on 23.01.2018.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: MainApplication): Context = application
}