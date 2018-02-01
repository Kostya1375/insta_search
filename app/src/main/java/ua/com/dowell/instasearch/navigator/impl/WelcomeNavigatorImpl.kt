package ua.com.dowell.instasearch.navigator.impl

import android.support.v4.app.FragmentActivity
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.navigator.WelcomeNavigator
import ua.com.dowell.instasearch.misc.replaceFragment
import ua.com.dowell.instasearch.view.activity.main.MainActivity
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramFragment

/**
 * Created by kosty on 29.01.2018.
 */
class WelcomeNavigatorImpl(private val activity: FragmentActivity) : WelcomeNavigator {
    override fun openLogin() {
        activity.replaceFragment(R.id.container, InstagramFragment.instance(), null)
    }

    override fun proceedToMainActivity() {
        MainActivity.start(activity, true)
    }
}