package ua.com.dowell.instasearch.model.impl

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by kosty on 02.02.2018.
 */
@SuppressLint("MissingPermission")
class PlayServicesLocationProvider @Inject constructor(context: Context) : AbsLocationProvider() {

    private val waitTime = TimeUnit.SECONDS.toMillis(30)
    private val service = LocationServices.getFusedLocationProviderClient(context)
    private val request = LocationRequest.create()
            .setInterval(periodMillis)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setMaxWaitTime(waitTime)
            .setSmallestDisplacement(1F)

    private val callback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            Timber.d("lastLocation: ${locationResult.lastLocation}")
            setNewLocation(locationResult.lastLocation)
        }
    }

    override fun startSpectating() {
        super.startSpectating()
        service.requestLocationUpdates(request, callback, null)
    }

    override fun stopSpectating() {
        super.stopSpectating()
        service.removeLocationUpdates(callback)
    }
}