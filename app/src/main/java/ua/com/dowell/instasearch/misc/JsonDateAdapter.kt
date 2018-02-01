package ua.com.dowell.instasearch.misc

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kosty on 30.01.2018.
 */
class JsonDateAdapter : TypeAdapter<Date>() {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    override fun write(out: JsonWriter?, value: Date?) {
        out?.value(dateFormat.format(value))
    }

    override fun read(`in`: JsonReader?): Date {
        val string = `in`?.nextString()
        return dateFormat.parse(string)
    }
}