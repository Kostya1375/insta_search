package ua.com.dowell.instasearch.model.pojo

/**
 * Created by kosty on 30.01.2018.
 */
data class LocationQuery(
        val location: Location,
        val distance: Int = 50
) {
    data class Location(
            val lat: Float,
            val lng: Float
    )
}