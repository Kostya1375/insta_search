package ua.com.dowell.instasearch.model

/**
 * Created by kosty on 23.01.2018.
 */
interface InstagramModel {

    val scheme: String
    val host: String
    val pathSegment1: String
    val pathSegment2: String
    val clientId: String
    val redirectUri: String
    val token: String
}