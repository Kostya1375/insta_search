package ua.com.dowell.instasearch.presenter.impl

import ua.com.dowell.instasearch.presenter.MainFragmentPresenter
import ua.com.dowell.instasearch.view.fragment.main.MainView

/**
 * Created by kosty on 29.01.2018.
 */
class MainFragmentPresenterImpl : MainFragmentPresenter {
    private var mainView: MainView? = null
    override fun setView(view: MainView) {
        mainView = view
    }

    override fun disposeView() {
        mainView = null
    }
}