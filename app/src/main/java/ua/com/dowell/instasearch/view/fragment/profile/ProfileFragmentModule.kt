package ua.com.dowell.instasearch.view.fragment.profile

import dagger.Module
import dagger.Provides
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.rest.Api
import ua.com.dowell.instasearch.presenter.ProfilePresenter
import ua.com.dowell.instasearch.presenter.impl.ProfilePresenterImpl

/**
 * Created by kosty on 31.01.2018.
 */
@Module
class ProfileFragmentModule {

    @Provides
    fun profileView(profileFragment: ProfileFragment): ProfileView {
        return profileFragment
    }

    @Provides
    fun profilePresenter(api: Api, accountHelper: AccountHelper): ProfilePresenter {
        return ProfilePresenterImpl(api, accountHelper)
    }
}