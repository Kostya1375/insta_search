package ua.com.dowell.instasearch.navigator.impl

import android.support.v4.app.FragmentActivity
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.navigator.WelcomeNavigator
import ua.com.dowell.instasearch.view.activity.main.MainActivity
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramFragment
import javax.inject.Inject

/**
 * Created by kosty on 29.01.2018.
 */
class WelcomeNavigatorImpl
@Inject
constructor(private val activity: FragmentActivity) : WelcomeNavigator {
    override fun openLogin() {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, InstagramFragment.instance())
                .addToBackStack(null)
                .commit()
    }

    override fun proceedToMainActivity() {
        MainActivity.start(activity, true)
    }
}