package ua.com.dowell.instasearch.presenter.impl

import android.webkit.WebViewClient
import okhttp3.HttpUrl
import timber.log.Timber
import ua.com.dowell.instasearch.misc.InstagramLoginWebViewClient
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.InstagramModel
import ua.com.dowell.instasearch.presenter.InstagramLoginPresenter
import ua.com.dowell.instasearch.view.InstagramView
import javax.inject.Inject

/**
 * Created by kosty on 23.01.2018.
 */
class InstagramLoginPresenterImpl @Inject constructor(
        private val accHelper: AccountHelper,
        private val instagramModel: InstagramModel
) : InstagramLoginPresenter {

    private val clientId = "client_id"
    private val redirectUrl = "redirect_uri"
    private val responseType = "response_type"

    private var iView: InstagramView? = null

    override fun setView(view: InstagramView) {
        this.iView = view
    }

    override fun disposeView() {
        iView = null
    }

    override fun createUrl(): String {
        val httpBuilder = HttpUrl.Builder().apply {
            scheme(instagramModel.scheme)
            host(instagramModel.host)
            addPathSegment(instagramModel.pathSegment1)
            addPathSegment(instagramModel.pathSegment2)
            addQueryParameter(clientId, instagramModel.clientId)
            addQueryParameter(redirectUrl, instagramModel.redirectUri)
            addQueryParameter(responseType, instagramModel.token)
        }
        return httpBuilder.build().toString()
    }

    override fun getRedirectUrl(): String = instagramModel.redirectUri
    override fun createWebViewClient(): WebViewClient = InstagramLoginWebViewClient(this, instagramModel)
    override fun onTokenReceived(token: String) {
        accHelper.saveToken(token)
        Timber.d("token: $token")
        iView?.dismissView()
    }
}