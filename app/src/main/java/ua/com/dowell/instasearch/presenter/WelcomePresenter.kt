package ua.com.dowell.instasearch.presenter

import android.support.v4.view.PagerAdapter
import ua.com.dowell.instasearch.view.fragment.welcome.WelcomeView

/**
 * Created by kosty on 29.01.2018.
 */
interface WelcomePresenter : BasePresenter<WelcomeView> {
    fun createAdapter(): PagerAdapter
    fun onLoginClick()
    fun checkIfLoggedIn()
}