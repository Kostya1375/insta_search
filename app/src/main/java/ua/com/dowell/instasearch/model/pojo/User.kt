package ua.com.dowell.instasearch.model.pojo

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import ua.com.dowell.instasearch.misc.JsonDateAdapter
import java.util.*

/**
 * Created by kosty on 30.01.2018.
 */
data class User(
        @SerializedName("_id")
        val id: String,
        @SerializedName("updatedAt")
        @JsonAdapter(JsonDateAdapter::class)
        val updated: Date,
        @SerializedName("createdAt")
        @JsonAdapter(JsonDateAdapter::class)
        val created: Date,
        @SerializedName("insta_id")
        val instagramId: Long,
        @SerializedName("username")
        val username: String,
        @SerializedName("profile_picture")
        val avatar: String,
        @SerializedName("full_name")
        val fullName: String
)