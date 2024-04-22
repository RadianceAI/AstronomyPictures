package by.radiance.space.pictures.presenter.ui.gallery.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.AstronomyPictureRepository
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.domain.utils.minusDays
import by.radiance.space.pictures.domain.utils.plusDays
import java.util.Date

class PictureSource(
    private val startDate: Date,
    private val endDate: Date,
    private val astronomyPictureRepository: AstronomyPictureRepository,
) : PagingSource<PictureSource.Key, Picture>() {

    override val jumpingSupported: Boolean = true

    override fun getRefreshKey(state: PagingState<Key, Picture>): Key? {
        val anchorPosition = state.anchorPosition

        return if (anchorPosition == null) {
            null
        } else {
            val pageSize = state.config.pageSize
            val pageIndex = anchorPosition / pageSize

            val endDateDelta = pageIndex * pageSize
            val startDateDelta = endDateDelta + pageSize - 1

            var keyStartDate = endDate.minusDays(startDateDelta)
            if (keyStartDate < startDate) {
                keyStartDate = startDate
            }

            Key(keyStartDate, endDate.minusDays(endDateDelta))
        }
    }

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Picture> {
        val key = params.key ?: Key(endDate.minusDays(params.loadSize - 1), endDate)

        return try {
            val pictures = astronomyPictureRepository.getPictures(
                startDate = key.startDate,
                endDate = key.endDate,
            ).reversed()

            LoadResult.Page(
                data = pictures,
                prevKey = if (params.key == null) null else prevKey(key, params.loadSize),
                nextKey = nextKey(key, params.loadSize),
                itemsBefore = if (params.key == null) 0 else itemsBefore(key),
                itemsAfter = itemsAfter(key),
            ).also {
                Log.d("TAG_TAG", "page: ${it.data.size} $key ${it.itemsBefore} ${it.itemsAfter}\n")
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun nextKey(
        key: Key,
        loadSize: Int,
    ): Key? {
        if (key.startDate == startDate) return null

        var newKeyStartDate = key.startDate.minusDays(loadSize)
        if (newKeyStartDate < startDate) {
            newKeyStartDate = startDate
        }

        return Key(newKeyStartDate, key.startDate.minusDays(1))
    }

    private fun prevKey(
        key: Key,
        loadSize: Int,
    ): Key {
        return Key(key.endDate.plusDays(1), key.endDate.plusDays(loadSize))
    }

    private fun itemsBefore(key: Key): Int {
        return DateUtil.daysBetweenDates(endDate, key.endDate)
    }

    private fun itemsAfter(key: Key): Int {
        if (key.endDate == startDate) {
            return 0
        }

        return DateUtil.daysBetweenDates(key.startDate, startDate)
    }

    data class Key(
        val startDate: Date,
        val endDate: Date,
    )
}