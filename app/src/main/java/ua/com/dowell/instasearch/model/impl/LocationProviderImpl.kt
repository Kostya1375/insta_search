package ua.com.dowell.instasearch.model.impl

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import timber.log.Timber
import ua.com.dowell.instasearch.model.LocationProvider
import ua.com.dowell.instasearch.model.pojo.LocationQuery
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by kosty on 31.01.2018.
 */
@SuppressLint("MissingPermission")
class LocationProviderImpl @Inject constructor(context: Context) : LocationProvider, LocationListener {

    private val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val handler = Handler()
    private val period = 3L
    private val periodMillis = TimeUnit.MINUTES.toMillis(period)
    private val runnable = {
        location?.let(this::setNewLocation)
        runHandler()
    }

    private var location: Location? = null
    private var simpleLocation: LocationQuery.Location? = null
    private var listener: ((location: Location) -> Unit)? = null
    private var simpleListener: ((location: LocationQuery.Location) -> Unit)? = null
    private var lastUpdate = SystemClock.uptimeMillis()

    override fun getSimpleLocation(): LocationQuery.Location? = simpleLocation
    override fun getLocation(): Location? = location

    override fun onNewLocation(listener: (location: Location) -> Unit) {
        this.listener = listener
    }

    override fun onNewSimpleLocation(simpleListener: (location: LocationQuery.Location) -> Unit) {
        this.simpleListener = simpleListener
    }

    override fun startSpectating() {
        Timber.d("Looking for location")
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F, this)
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0F, this)
        runHandler()
    }

    private fun runHandler() {
        handler.postDelayed(runnable, periodMillis)
    }

    override fun stopSpectating() {
        Timber.d("Stopping looking for location")
        manager.removeUpdates(this)
        handler.removeCallbacks(runnable)
    }

    override fun onLocationChanged(location: Location?) {
        Timber.d("New Location got!: $location")
        location ?: return

        val currentMillis = SystemClock.uptimeMillis()
        val minutes = TimeUnit.MILLISECONDS.toMinutes(currentMillis - lastUpdate)
        if (minutes < period && this.location != null) return
        lastUpdate = currentMillis
        setNewLocation(location)
    }

    private fun setNewLocation(location: Location) {
        val simpleLocation = LocationQuery.Location(location.latitude.toFloat(), location.longitude.toFloat())
        this.simpleLocation = simpleLocation
        this.location = location
        simpleListener?.invoke(simpleLocation)
        listener?.invoke(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) = Unit
    override fun onProviderEnabled(provider: String?) = Unit
    override fun onProviderDisabled(provider: String?) = Unit
}