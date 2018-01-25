package ua.com.dowell.instasearch.misc

import android.annotation.TargetApi
import android.net.Uri
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import ua.com.dowell.instasearch.model.InstagramModel
import ua.com.dowell.instasearch.presenter.InstagramLoginPresenter

/**
 * Created by kosty on 23.01.2018.
 */
class InstagramLoginWebViewClient(private val presenter: InstagramLoginPresenter,
                                  private val instagramModel: InstagramModel) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return interceptUrlLoading(url)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return interceptUrlLoading(request?.url.toString())
    }

    private fun interceptUrlLoading(url: String?): Boolean {
        if (url?.startsWith(instagramModel.redirectUri) == true) {
            val uri = Uri.parse(url)
            val token = uri.fragment.removePrefix("access_token=")
            presenter.onTokenReceived(token)
        }
        return false
    }
}