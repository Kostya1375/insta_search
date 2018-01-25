package ua.com.dowell.instasearch.view.activity.login

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramFragment

class LoginActivity : DaggerAppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, InstagramFragment.instance())
                .commit()
    }
}
