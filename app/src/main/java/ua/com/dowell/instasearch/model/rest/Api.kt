package ua.com.dowell.instasearch.model.rest

import com.google.gson.JsonObject
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ua.com.dowell.instasearch.model.pojo.LocationQuery
import ua.com.dowell.instasearch.model.pojo.User

/**
 * Created by kosty on 18.01.2018.
 */
interface Api {

    @POST("location")
    fun getNearby(@Body location: LocationQuery): Deferred<List<User>>

    @GET("me")
    fun myInfo(): Deferred<User>

    @POST("login")
    fun login(@Body token: JsonObject): Deferred<JsonObject>
}