package by.radiance.space.source

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.toId
import by.radiance.space.pictures.domain.repository.AstronomyPictureRepository
import by.radiance.space.pictures.domain.utils.plusDays
import java.util.Date

class AstronomyPictureSource(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource,
) : AstronomyPictureRepository {
    private val pictures: MutableMap<Id, Picture> = mutableMapOf()

    override suspend fun getPictures(startDate: Date, endDate: Date): List<Picture> {
        val ids = rangeFromDates(startDate, endDate)

        val inMemoryPictures = getInMemoryPicture(ids)

        if (getMissingIds(ids, inMemoryPictures).isNotEmpty()) {
            return inMemoryPictures
        }

        val localPictures = localSource.getPictures(startDate, endDate)

        if (getMissingIds(ids, localPictures).isNotEmpty()) {
            localPictures.forEach {
                pictures[it.id] = it
            }

            return localPictures
        }

        val remotePictures = remoteSource.getPictures(startDate, endDate)

        return remotePictures.also {
            localSource.save(it)
        }
    }

    private fun getMissingIds(ids: List<Id>, pictures: List<Picture>): List<Id> {
        return ids.filter { id ->
            !pictures.none { picture ->
                picture.id == id
            }
        }
    }

    private fun getInMemoryPicture(ids: List<Id>): List<Picture> {
        val inMemoryPictures = mutableListOf<Picture>()

        ids.forEach { id ->
            val picture = pictures[id]
            if (picture != null) {
                inMemoryPictures.add(picture)
            }
        }

        return inMemoryPictures
    }

    private fun rangeFromDates(startDate: Date, endDate: Date): List<Id> {
        val dates = mutableListOf<Id>()

        var date = startDate

        while (date != endDate) {
            dates.add(date.toId())
            date = date.plusDays(1)
        }

        dates.add(date.toId())

        return dates
    }
}