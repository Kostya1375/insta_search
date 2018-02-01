package ua.com.dowell.instasearch.view.fragment.main

import ua.com.dowell.instasearch.model.pojo.User
import ua.com.dowell.instasearch.view.BaseView

/**
 * Created by kosty on 29.01.2018.
 */
interface MainView : BaseView {
    fun checkPermissions(): Boolean
    fun requestPermissions()
    fun showUsers(list: List<User>)
    fun showEmptyPlaceholder()
    fun showError()
}