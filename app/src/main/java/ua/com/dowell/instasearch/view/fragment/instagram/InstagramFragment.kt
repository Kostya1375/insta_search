package ua.com.dowell.instasearch.view.fragment.instagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.*
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.presenter.InstagramLoginPresenter
import javax.inject.Inject

/**
 * Created by kosty on 23.01.2018.
 */
class InstagramFragment : DaggerFragment(), InstagramView {
    @Inject
    lateinit var presenter: InstagramLoginPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        val url = presenter.createUrl()
        web_view.webViewClient = presenter.createWebViewClient()
        web_view.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        web_view.settings.saveFormData = false
        web_view.settings.setAppCacheEnabled(false)
        web_view.settings.javaScriptEnabled = true
        web_view.clearCache(true)
        web_view.loadUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.disposeView()
    }

    override fun dismissView() {
//        todo other implementation
        activity.onBackPressed()
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        progress_bar.visibility = View.GONE
    }

    companion object {
        fun instance(): InstagramFragment {
            return InstagramFragment()
        }
    }
}