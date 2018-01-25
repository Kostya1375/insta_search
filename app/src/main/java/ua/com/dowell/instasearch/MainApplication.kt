package ua.com.dowell.instasearch

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import ua.com.dowell.instasearch.di.DaggerAppComponent

/**
 * Created by kosty on 23.01.2018.
 */
class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}