package ua.com.dowell.instasearch.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ua.com.dowell.instasearch.view.activity.login.LoginActivity
import ua.com.dowell.instasearch.view.activity.login.LoginActivityModule
import ua.com.dowell.instasearch.view.activity.main.MainActivity
import ua.com.dowell.instasearch.view.activity.main.MainActivityModule

/**
 * Created by kosty on 24.01.2018.
 */
@Module
interface ActivityBuilder {
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    fun loginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity
}