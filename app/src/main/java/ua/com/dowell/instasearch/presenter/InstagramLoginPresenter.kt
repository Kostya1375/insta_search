package ua.com.dowell.instasearch.presenter

import android.webkit.WebViewClient
import ua.com.dowell.instasearch.view.InstagramView

/**
 * Created by kosty on 23.01.2018.
 */
interface InstagramLoginPresenter {

    fun createUrl(): String
    fun getRedirectUrl(): String
    fun createWebViewClient(): WebViewClient
    fun onTokenReceived(token: String)
    fun setView(view: InstagramView)
    fun disposeView()
}