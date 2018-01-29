package ua.com.dowell.instasearch.view.fragment.instagram

import dagger.Module
import dagger.Provides
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.InstagramUrlModel
import ua.com.dowell.instasearch.presenter.InstagramLoginPresenter
import ua.com.dowell.instasearch.presenter.impl.InstagramLoginPresenterImpl

/**
 * Created by kosty on 24.01.2018.
 */
@Module
class InstagramFragmentModule {

    @Provides
    fun instagramFragmentView(instagramFragment: InstagramFragment): InstagramView {
        return instagramFragment
    }

    @Provides
    fun instagramPresenter(accountHelper: AccountHelper, instagramModel: InstagramUrlModel): InstagramLoginPresenter {
        return InstagramLoginPresenterImpl(accountHelper, instagramModel)
    }
}