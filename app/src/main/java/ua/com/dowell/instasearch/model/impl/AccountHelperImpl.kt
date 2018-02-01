package ua.com.dowell.instasearch.model.impl

import android.content.Context
import android.preference.PreferenceManager
import ua.com.dowell.instasearch.Constants
import ua.com.dowell.instasearch.model.AccountHelper
import javax.inject.Inject

/**
 * Created by kosty on 23.01.2018.
 */

class AccountHelperImpl @Inject constructor(context: Context) : AccountHelper {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun saveToken(token: String) = preferences.edit().putString(Constants.TOKEN, token).apply()
    override fun getToken(): String = preferences.getString(Constants.TOKEN, "")
    override fun clearToken() = preferences.edit().remove(Constants.TOKEN).apply()

    override fun setDistanceSettings(value: Int) = preferences.edit().putInt(Constants.DISTANCE, value).apply()
    override fun getDistanceSettings(): Int = preferences.getInt(Constants.DISTANCE, 10)
}