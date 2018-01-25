package ua.com.dowell.instasearch.view.activity.login

import dagger.Module
import dagger.Provides

/**
 * Created by kosty on 24.01.2018.
 */
@Module
class LoginActivityModule {

    @Provides
    fun loginView(loginActivity: LoginActivity): LoginView = loginActivity
}