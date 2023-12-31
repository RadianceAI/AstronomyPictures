package by.radiance.space.pictures.presenter.ui.gallery.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.utils.minusDays
import by.radiance.space.pictures.domain.utils.plusDays
import java.util.Date

class PictureSource(
    private val astronomyPictureRepository: RemoteAstronomyPictureRepository,
) : PagingSource<PictureSource.Key, Picture>() {

    override val jumpingSupported: Boolean = true

    override fun getRefreshKey(state: PagingState<Key, Picture>): Key? {
        return state.closestPageToPosition(state.anchorPosition?: 0)?.prevKey
    }

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Picture> {
        val today = Date()
        val key = params.key ?: Key(today.minusDays(params.loadSize), today.minusDays(1))

        return try {
            val pictures = astronomyPictureRepository.getPictures(
                startDate = key.startDate,
                endDate = key.endDate,
            ).reversed()

            LoadResult.Page(
                data = pictures,
                prevKey = if (params.key == null) null else prevKey(key, params.loadSize),
                nextKey = nextKey(key, params.loadSize),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun nextKey(
        key: Key,
        loadSize: Int,
    ): Key {
        return Key(key.startDate.minusDays(1 + loadSize), key.startDate.minusDays(1))
    }

    private fun prevKey(
        key: Key,
        loadSize: Int,
    ): Key {
        return Key(key.endDate.plusDays(1), key.endDate.plusDays(1 + loadSize))
    }

    data class Key(
        val startDate: Date,
        val endDate: Date,
    )
}