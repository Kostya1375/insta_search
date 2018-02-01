package ua.com.dowell.instasearch.presenter

import ua.com.dowell.instasearch.view.fragment.main.MainView

/**
 * Created by kosty on 29.01.2018.
 */
interface MainFragmentPresenter : BasePresenter<MainView> {
    fun startSpectate()
    fun stopSpectate()
    fun onPermissionGranted()
}