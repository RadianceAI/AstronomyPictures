package by.radiance.space.pictures.domain.entity

sealed class PictureSource

data class Image(val huge: String, val light: String): PictureSource()
data class Video(val src: String, val thumbnail: String): PictureSource()
