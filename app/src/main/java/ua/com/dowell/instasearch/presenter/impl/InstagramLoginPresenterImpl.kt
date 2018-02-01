package ua.com.dowell.instasearch.presenter.impl

import android.webkit.WebViewClient
import com.google.gson.JsonObject
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import okhttp3.HttpUrl
import ua.com.dowell.instasearch.misc.InstagramLoginWebViewClient
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.InstagramUrlModel
import ua.com.dowell.instasearch.model.rest.Api
import ua.com.dowell.instasearch.presenter.InstagramLoginPresenter
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramView
import javax.inject.Inject

/**
 * Created by kosty on 23.01.2018.
 */
class InstagramLoginPresenterImpl @Inject constructor(
        private val api: Api,
        private val accHelper: AccountHelper,
        private val instagramModel: InstagramUrlModel
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
    override fun createWebViewClient(): WebViewClient {
        val onPageFinished: () -> Unit = { iView?.dismissProgressBar() }
        val onPageStarted: () -> Unit = { iView?.showProgressBar() }
        return InstagramLoginWebViewClient(this, instagramModel, onPageFinished, onPageStarted)
    }

    override fun onTokenReceived(token: String) {
        launch {
            val jsonObject = JsonObject()
            jsonObject.addProperty("token", token)
            val jwtToken = api.login(jsonObject).await()["token"].asString
            accHelper.saveToken(jwtToken)
            launch(UI) { iView?.dismissView() }
        }
    }
}