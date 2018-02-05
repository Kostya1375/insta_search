package ua.com.dowell.instasearch.view.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.misc.replaceFragment
import ua.com.dowell.instasearch.view.fragment.main.MainFragment
import ua.com.dowell.instasearch.view.fragment.profile.ProfileFragment

/**
 * Created by kosty on 25.01.2018.
 */
class MainActivity : DaggerAppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        replaceFragment(R.id.container, MainFragment.instance())
    }

    companion object {
        fun start(context: Context, isClear: Boolean = false) {
            val intent = Intent(context, MainActivity::class.java)
            if (isClear) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }
}