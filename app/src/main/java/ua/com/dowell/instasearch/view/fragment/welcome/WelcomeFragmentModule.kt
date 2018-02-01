package ua.com.dowell.instasearch.view.fragment.welcome

import dagger.Module
import dagger.Provides
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.navigator.WelcomeNavigator
import ua.com.dowell.instasearch.navigator.impl.WelcomeNavigatorImpl
import ua.com.dowell.instasearch.presenter.WelcomePresenter
import ua.com.dowell.instasearch.presenter.impl.WelcomePresenterImpl

/**
 * Created by kosty on 29.01.2018.
 */
@Module
class WelcomeFragmentModule {

    @Provides
    fun welcomeFragmentView(welcomeFragment: WelcomeFragment): WelcomeView {
        return welcomeFragment
    }

    @Provides
    fun welcomePresenter(accountHelper: AccountHelper, welcomeNavigator: WelcomeNavigator): WelcomePresenter {
        return WelcomePresenterImpl(accountHelper, welcomeNavigator)
    }

    @Provides
    fun welcomeNavigator(welcomeFragment: WelcomeFragment): WelcomeNavigator {
        return WelcomeNavigatorImpl(welcomeFragment.activity)
    }
}