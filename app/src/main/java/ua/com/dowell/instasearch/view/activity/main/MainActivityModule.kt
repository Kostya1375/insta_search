package ua.com.dowell.instasearch.view.activity.main

import dagger.Module
import dagger.Provides

/**
 * Created by kosty on 25.01.2018.
 */
@Module
class MainActivityModule {

    @Provides
    fun mainView(mainActivity: MainActivity): MainView = mainActivity
}