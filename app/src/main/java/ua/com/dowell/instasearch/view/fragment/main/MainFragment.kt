package ua.com.dowell.instasearch.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.presenter.MainFragmentPresenter
import javax.inject.Inject

/**
 * Created by kosty on 29.01.2018.
 */
class MainFragment : DaggerFragment(), MainView {

    @Inject
    lateinit var presenter: MainFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.disposeView()
    }

    companion object {
        fun instance(): MainFragment {
            return MainFragment()
        }
    }
}