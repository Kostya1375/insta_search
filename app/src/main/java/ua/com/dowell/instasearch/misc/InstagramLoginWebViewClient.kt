package ua.com.dowell.instasearch.misc

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber
import ua.com.dowell.instasearch.model.InstagramUrlModel
import ua.com.dowell.instasearch.presenter.InstagramLoginPresenter

/**
 * Created by kosty on 23.01.2018.
 */
class InstagramLoginWebViewClient(private val presenter: InstagramLoginPresenter,
                                  private val instagramModel: InstagramUrlModel,
                                  private val onPageFinishedListener: (() -> Unit)? = null,
                                  private val onPageStartedListener: (() -> Unit)? = null) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return interceptUrlLoading(url)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return interceptUrlLoading(request?.url.toString())
    }

    private fun interceptUrlLoading(url: String?): Boolean {
        Timber.d("url: $url")
        if (url?.startsWith(instagramModel.redirectUri) == true) {
            val uri = Uri.parse(url)
            val token = uri.fragment.removePrefix("access_token=")
            presenter.onTokenReceived(token)
            return true
        }
        return false
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        onPageFinishedListener?.invoke()
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        onPageStartedListener?.invoke()
    }
}