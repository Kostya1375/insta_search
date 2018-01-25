package ua.com.dowell.instasearch.view.fragment.instagram

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import dagger.android.support.DaggerFragment
import okhttp3.OkHttpClient
import timber.log.Timber
import ua.com.dowell.instasearch.presenter.InstagramLoginPresenter
import ua.com.dowell.instasearch.view.InstagramView
import javax.inject.Inject

/**
 * Created by kosty on 23.01.2018.
 */
class InstagramFragment : DaggerFragment(), InstagramView {

    @Inject
    lateinit var presenter: InstagramLoginPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val webView = WebView(context)
        val params = ViewGroup.LayoutParams(-1, -1)
        webView.layoutParams = params
        webView.setBackgroundColor(Color.BLUE)
        return webView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = view as WebView
        presenter.setView(this)
        val url = presenter.createUrl()
        Timber.d("URL: $url")
        webView.webViewClient = presenter.createWebViewClient()
        webView.clearCache(true)
        webView.loadUrl(url)
    }

    override fun dismissView() {
//        todo other implementation
        activity.finish()
    }

    companion object {
        fun instance(): InstagramFragment {
            return InstagramFragment()
        }
    }
}