package ua.com.dowell.instasearch.presenter.impl

import android.os.Handler
import android.support.v4.view.PagerAdapter
import ua.com.dowell.instasearch.adapter.WelcomeAdapter
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.navigator.WelcomeNavigator
import ua.com.dowell.instasearch.presenter.WelcomePresenter
import ua.com.dowell.instasearch.view.fragment.welcome.WelcomeView

/**
 * Created by kosty on 29.01.2018.
 */
class WelcomePresenterImpl(
        private val accountHelper: AccountHelper,
        private val navigator: WelcomeNavigator
) : WelcomePresenter {

    private var welcomeView: WelcomeView? = null
    private var handler: Handler? = Handler()

    override fun setView(view: WelcomeView) {
        welcomeView = view
        if (welcomeView != null) onViewSet()
    }

    override fun disposeView() {
        handler = null
        welcomeView = null
    }

    override fun onLoginClick() {
        navigator.openLogin()
    }

    override fun checkIfLoggedIn() {
        if (accountHelper.getToken().isNotBlank())
            navigator.proceedToMainActivity()
    }

    override fun createAdapter(): PagerAdapter = WelcomeAdapter()

    private fun onViewSet() {
        queueSwipe()
    }

    private fun queueSwipe() {
        handler?.postDelayed({
            welcomeView?.swipeView()
            queueSwipe()
        }, 5000)
    }
}