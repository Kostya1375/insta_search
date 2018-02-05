package ua.com.dowell.instasearch.model.impl

import android.location.Location
import android.os.Handler
import timber.log.Timber
import ua.com.dowell.instasearch.model.LocationProvider
import ua.com.dowell.instasearch.model.pojo.LocationQuery
import java.util.concurrent.TimeUnit


/**
 * Created by kosty on 02.02.2018.
 */
abstract class AbsLocationProvider : LocationProvider {

    internal var location: Location? = null
    private var simpleLocation: LocationQuery.Location? = null
    private var listener: ((location: Location) -> Unit)? = null
    private var simpleListener: ((location: LocationQuery.Location) -> Unit)? = null

    internal val period = 1L
    internal val periodMillis = TimeUnit.MINUTES.toMillis(period)
    private val handler = Handler()
    private val runnable = {
        location?.let(this::setNewLocation)
        runHandler()
    }


    override fun getSimpleLocation(): LocationQuery.Location? = simpleLocation
    override fun getLocation(): Location? = location

    override fun startSpectating() {
        runHandler()
    }

    override fun stopSpectating() {
        handler.removeCallbacks(runnable)
    }

    override fun onNewLocation(listener: (location: Location) -> Unit) {
        this.listener = listener
    }

    override fun onNewSimpleLocation(simpleListener: (location: LocationQuery.Location) -> Unit) {
        this.simpleListener = simpleListener
    }

    fun setNewLocation(location: Location) {
        Timber.d("New location acquired! $location")
        val simpleLocation = LocationQuery.Location(location.latitude.toFloat(), location.longitude.toFloat())
        this.simpleLocation = simpleLocation
        this.location = location
        simpleListener?.invoke(simpleLocation)
        listener?.invoke(location)
    }

    private fun runHandler() {
        handler.postDelayed(runnable, periodMillis)
    }
}