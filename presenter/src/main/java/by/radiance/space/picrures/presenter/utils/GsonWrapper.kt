package by.radiance.space.picrures.presenter.utils

import by.radiance.space.pictures.domain.entity.Id
import com.google.gson.*
import java.sql.Date


object GsonWrapper {
    private var ser: JsonSerializer<Date> =
        JsonSerializer { src, typeOfSrc, context -> if (src == null) null else JsonPrimitive(src.time) }


    private var deser: JsonDeserializer<Date> =
        JsonDeserializer { json, typeOfT, context -> if (json == null) null else Date(json.asLong) }

    private val idSer: JsonSerializer<Id> =
        JsonSerializer { src, typeOfSrc, context ->
            if (src == null) null else JsonObject().apply {
                this.add("date", JsonPrimitive(src.date.time))
                this.add("isRandom", JsonPrimitive(src.isRandom))
            }
        }

    private val idDeser: JsonDeserializer<Id> =
        JsonDeserializer { json, typeOfT, context ->
            if (json == null) null else Id(
                date = Date(json.asJsonObject?.get("date")?.asLong!!),
                isRandom = json.asJsonObject?.get("isRandom")?.asBoolean!!
            )
        }

    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, ser)
        .registerTypeAdapter(Date::class.java, deser)
        .registerTypeAdapter(Id::class.java, idSer)
        .registerTypeAdapter(Id::class.java, idDeser)
        .create()

}
