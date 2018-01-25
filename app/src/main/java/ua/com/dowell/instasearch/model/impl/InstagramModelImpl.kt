package ua.com.dowell.instasearch.model.impl

import android.content.Context
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.model.InstagramModel
import javax.inject.Inject

/**
 * Created by kosty on 23.01.2018.
 */
class InstagramModelImpl @Inject constructor(context: Context) : InstagramModel {
    override val scheme: String = "https"
    override val host: String = "api.instagram.com"
    override val pathSegment1: String = "oauth"
    override val pathSegment2: String = "authorize"
    override val clientId: String = context.getString(R.string.instagram_client_id)
    override val redirectUri: String = context.getString(R.string.instagram_redirect_uri)
    override val token = "token"
}