package by.radiance.space.pictures.domain.entity

sealed class Source

data class Image(val huge: String, val light: String): Source()
data class Video(val src: String, val thumbnail: String): Source()
