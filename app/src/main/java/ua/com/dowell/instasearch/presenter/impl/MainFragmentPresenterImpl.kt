package ua.com.dowell.instasearch.presenter.impl

import android.support.v4.widget.SwipeRefreshLayout
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.LocationProvider
import ua.com.dowell.instasearch.model.pojo.LocationQuery
import ua.com.dowell.instasearch.model.rest.Api
import ua.com.dowell.instasearch.presenter.MainFragmentPresenter
import ua.com.dowell.instasearch.view.fragment.main.MainView

/**
 * Created by kosty on 29.01.2018.
 */
class MainFragmentPresenterImpl(
        private val api: Api,
        private val accountHelper: AccountHelper,
        private val locationProvider: LocationProvider
) : MainFragmentPresenter {

    private var mainView: MainView? = null

    private val listener: SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        val location = locationProvider.getSimpleLocation()
        location?.let { requestNearbyUsers(it) }
    }

    override fun setView(view: MainView) {
        mainView = view
        if (mainView != null) onViewSet()
    }

    private fun onViewSet() {
        if (mainView?.checkPermissions() != true) mainView?.requestPermissions()
        else onPermissionGranted()
        mainView?.setSwipeListener(listener)
    }

    override fun onPermissionGranted() {
        locationProvider.onNewSimpleLocation {
            Timber.d("Location: $it")
            requestNearbyUsers(it)
        }
    }

    private fun requestNearbyUsers(location: LocationQuery.Location) {
        async {
            try {
                val distance = accountHelper.getDistanceSettings()
                val list = api.getNearby(LocationQuery(location, distance)).await()
                launch(UI) {
                    if (list.isNotEmpty()) {
                        mainView?.onUpdateFinish()
                        mainView?.showUsers(list)
                    } else mainView?.showEmptyPlaceholder()
                }
            } catch (e: Exception) {
                Timber.e(e)
                launch(UI) {
                    mainView?.showError()
                }
            }
        }
    }

    override fun startSpectate() {
        if (mainView?.checkPermissions() == true) locationProvider.startSpectating()
        else Timber.d("Permission not granted!!")
    }

    override fun stopSpectate() {
        if (mainView?.checkPermissions() == true) locationProvider.stopSpectating()
        else Timber.d("Permission not granted!!")
    }

    override fun disposeView() {
        mainView = null
    }
}