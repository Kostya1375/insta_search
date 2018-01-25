package ua.com.dowell.instasearch

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by kosty on 25.01.2018.
 */
fun AppCompatActivity.replaceFragment(
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

fun AppCompatActivity.replaceFragment(
        containerId: Int = R.id.container,
        fragment: Fragment
) {
    this.supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
}

fun AppCompatActivity.replaceFragment(
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

fun AppCompatActivity.replaceFragment(
        containerId: Int = R.id.container,
        fragment: android.app.Fragment
) {
    this.fragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
}