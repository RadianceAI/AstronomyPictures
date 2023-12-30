package by.radiance.space.pictures.presenter.utils

import by.radiance.space.pictures.domain.entity.Id
import com.google.gson.*
import java.sql.Date


object GsonWrapper {
    private var ser: JsonSerializer<Date> =
        JsonSerializer { src, typeOfSrc, context -> if (src == null) null else JsonPrimitive(src.time) }


    private var deser: JsonDeserializer<Date> =
        JsonDeserializer { json, typeOfT, context -> if (json == null) null else Date(json.asLong) }

    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, ser)
        .registerTypeAdapter(Date::class.java, deser)
        .create()
}
