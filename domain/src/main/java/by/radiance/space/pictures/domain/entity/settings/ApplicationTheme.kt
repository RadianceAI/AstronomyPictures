package by.radiance.space.pictures.domain.entity.settings

sealed class ApplicationTheme {
    data object Dark : ApplicationTheme()
    data object Light : ApplicationTheme()
    data object System : ApplicationTheme()

    companion object {
        fun toIndex(theme: ApplicationTheme): Int {
            return when (theme) {
                Dark -> 0
                Light -> 1
                System -> 2
            }
        }

        fun fromIndex(index: Int): ApplicationTheme {
            return when (index) {
                0 -> Dark
                1 -> Light
                else -> System
            }
        }
    }
}