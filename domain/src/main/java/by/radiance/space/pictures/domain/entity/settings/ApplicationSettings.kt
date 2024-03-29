package by.radiance.space.pictures.domain.entity.settings

data class ApplicationSettings(
    val theme: ApplicationTheme,
    val cornersSize: CornersSize,
    val safeArea: SafeArea,
    val listArrangement: ListArrangement
)