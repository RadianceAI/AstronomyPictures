package by.radiance.space.pictures.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NasaAstronomyPicture(

	@field:Json(name="date")
	val date: String,

	@field:Json(name="copyright")
	val copyright: String?,

	// "other"
	// "image"
	// "video"
	@field:Json(name="media_type")
	val mediaType: String,

	@field:Json(name="hdurl")
	val hdurl: String,

	@field:Json(name="service_version")
	val serviceVersion: String,

	@field:Json(name="explanation")
	val explanation: String,

	@field:Json(name="title")
	val title: String,

	@field:Json(name="url")
	val url: String
)
