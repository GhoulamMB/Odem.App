package fin.tech.odem.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date


class CustomDateTypeAdapter : JsonSerializer<Date?>,
    JsonDeserializer<Date> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", java.util.Locale.getDefault())

    @Synchronized
    override fun serialize(
        date: Date?,
        type: Type,
        jsonSerializationContext: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(date?.let { dateFormat.format(it) })
    }

    @Synchronized
    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): Date {
        return try {
            dateFormat.parse(jsonElement.asString)!!
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }
}
