package ua.com.dowell.instasearch.view.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.support.DaggerAppCompatActivity
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.view.fragment.main.MainFragment

/**
 * Created by kosty on 25.01.2018.
 */
class MainActivity : DaggerAppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.instance())
                .commit()
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