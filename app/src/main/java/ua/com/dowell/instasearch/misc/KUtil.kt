package ua.com.dowell.instasearch.misc

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import ua.com.dowell.instasearch.R

/**
 * Created by kosty on 25.01.2018.
 */
fun FragmentActivity.replaceFragment(
        containerId: Int = R.id.container,
        fragment: Fragment,
        backStackEntry: String?
) {
    this.supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(backStackEntry)
            .commit()
}

fun FragmentActivity.replaceFragment(
        containerId: Int = R.id.container,
        fragment: Fragment
) {
    this.supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
}

fun FragmentActivity.replaceFragment(
        containerId: Int = R.id.container,
        fragment: android.app.Fragment,
        backStackEntry: String?
) {
    this.fragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(backStackEntry)
            .commit()
}

fun FragmentActivity.replaceFragment(
        containerId: Int = R.id.container,
        fragment: android.app.Fragment
) {
    this.fragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
}