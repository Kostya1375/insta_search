package ua.com.dowell.instasearch.model

import android.location.Location
import ua.com.dowell.instasearch.model.pojo.LocationQuery

/**
 * Created by kosty on 31.01.2018.
 */
interface LocationProvider {
    fun getSimpleLocation(): LocationQuery.Location?
    fun getLocation(): Location?
    fun onNewLocation(listener: (location: Location) -> Unit)
    fun onNewSimpleLocation(simpleListener: (location: LocationQuery.Location) -> Unit)
    fun startSpectating()
    fun stopSpectating()
}