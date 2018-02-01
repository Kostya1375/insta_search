package ua.com.dowell.instasearch.view.fragment.main

import dagger.Module
import dagger.Provides
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.LocationProvider
import ua.com.dowell.instasearch.model.rest.Api
import ua.com.dowell.instasearch.navigator.MainFragmentNavigator
import ua.com.dowell.instasearch.navigator.impl.MainFragmentNavigatorImpl
import ua.com.dowell.instasearch.presenter.MainFragmentPresenter
import ua.com.dowell.instasearch.presenter.impl.MainFragmentPresenterImpl

/**
 * Created by kosty on 29.01.2018.
 */
@Module
class MainFragmentModule {

    @Provides
    fun mainFragmentView(mainFragment: MainFragment): MainView {
        return mainFragment
    }

    @Provides
    fun mainFragmentPresenter(api: Api, accountHelper: AccountHelper, locationProvider: LocationProvider): MainFragmentPresenter {
        return MainFragmentPresenterImpl(api, accountHelper, locationProvider)
    }

    @Provides
    fun mainFragmentNavigator(mainFragment: MainFragment): MainFragmentNavigator {
        return MainFragmentNavigatorImpl(mainFragment.activity)
    }

}