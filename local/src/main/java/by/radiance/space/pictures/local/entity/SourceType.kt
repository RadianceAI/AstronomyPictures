package by.radiance.space.pictures.local.entity

enum class SourceType(val id: Int) {
    Image(0),
    Video(1);

    companion object {
        fun getById(id: Int): SourceType {
            return when (id) {
                Image.id -> Image
                Video.id -> Video
                else -> Image
            }
        }
    }
}