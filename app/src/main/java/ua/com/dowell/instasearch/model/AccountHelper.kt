package ua.com.dowell.instasearch.model

/**
 * Created by kosty on 23.01.2018.
 */
interface AccountHelper {

    fun saveToken(token: String)
    fun getToken(): String
    fun clearToken()

    fun getDistanceSettings(): Int
    fun setDistanceSettings(value: Int)
}