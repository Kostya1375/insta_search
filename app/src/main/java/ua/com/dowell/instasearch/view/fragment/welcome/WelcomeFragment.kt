package ua.com.dowell.instasearch.view.fragment.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_welcome.*
import timber.log.Timber
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.presenter.WelcomePresenter
import javax.inject.Inject

/**
 * Created by kosty on 29.01.2018.
 */
class WelcomeFragment : DaggerFragment(), WelcomeView {
    @Inject
    lateinit var presenter: WelcomePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = presenter.createAdapter()
        presenter.setView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.disposeView()
    }

    override fun onResume() {
        super.onResume()
        presenter.checkIfLoggedIn()
    }

    override fun handleLoginClick() {
        login_button.setOnClickListener {
            Timber.d("click")
            presenter.onLoginClick()
        }
    }

    override fun swipeView() {
        val lastIndex = view_pager.adapter.count - 1
        val currentItem = view_pager.currentItem
        val nextItem = if (currentItem < lastIndex) currentItem + 1 else 0
        view_pager.currentItem = nextItem
    }

    companion object {
        fun instance(): WelcomeFragment {
            return WelcomeFragment()
        }
    }
}