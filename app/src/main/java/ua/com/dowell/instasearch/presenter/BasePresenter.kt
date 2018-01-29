package ua.com.dowell.instasearch.presenter

import ua.com.dowell.instasearch.view.BaseView

/**
 * Created by kosty on 29.01.2018.
 */
interface BasePresenter<in T : BaseView> {

    fun setView(view: T)
    fun disposeView()
}