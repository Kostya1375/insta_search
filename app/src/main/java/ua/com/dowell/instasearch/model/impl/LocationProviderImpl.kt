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
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by kosty on 31.01.2018.
 */
@SuppressLint("MissingPermission")
class LocationProviderImpl @Inject constructor(context: Context) : AbsLocationProvider(), LocationListener {

    private val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private var lastUpdate = SystemClock.uptimeMillis()
    private var onResume = true

    override fun startSpectating() {
        super.startSpectating()
        Timber.d("Looking for location")
        onResume = true
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F, this)
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0F, this)
    }

    override fun stopSpectating() {
        super.stopSpectating()
        manager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location?) {
        Timber.d("New Location got!: $location")
        location ?: return

        val currentMillis = SystemClock.uptimeMillis()
        val minutes = TimeUnit.MILLISECONDS.toMinutes(currentMillis - lastUpdate)
        if (minutes < period && !onResume) return
        lastUpdate = currentMillis
        onResume = false
        setNewLocation(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) = Unit
    override fun onProviderEnabled(provider: String?) = Unit
    override fun onProviderDisabled(provider: String?) = Unit
}