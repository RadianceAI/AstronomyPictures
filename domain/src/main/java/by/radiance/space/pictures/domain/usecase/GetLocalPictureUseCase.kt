package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Group
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import java.util.*

class GetLocalPictureUseCase(
    private val localRepository: LocalRepository,
) {
    fun get(): Flow<Map<Group, List<Picture>>> {
        return localRepository.getAll().map { list ->
            val monthGrouped = list.groupBy { picture ->
                Group.Month(
                    Calendar.getInstance().apply {
                        time = picture.id.date
                        set(Calendar.DAY_OF_MONTH, 1)
                    }.time
                ) as Group
            }

            val years = monthGrouped.keys.groupBy { month ->
                Group.Year(
                    Calendar.getInstance().apply {
                        time = when(month) {
                            is Group.Month -> month.date
                            is Group.Year -> month.date
                            is Group.Decade -> month.date
                        }
                        set(Calendar.MONTH, 1)
                    }.time
                ) as Group
            }

            val yearGrouped = mergeTinyGroups(years, monthGrouped)

            val decades = yearGrouped.keys.groupBy { year ->
                Group.Decade(
                    Calendar.getInstance().apply {
                        time = when(year) {
                            is Group.Month -> year.date
                            is Group.Year -> year.date
                            is Group.Decade -> year.date
                        }
                        set(Calendar.MONTH, 1)
                        set(Calendar.YEAR, (get(Calendar.YEAR) / 10) * 10 )
                    }.time
                ) as Group
            }

            val decadeGrouped = mergeTinyGroups(decades, yearGrouped)

            decadeGrouped
        }
    }

    private fun mergeTinyGroups(
        nextGroups: Map<Group, List<Group>>,
        grouped: Map<Group, List<Picture>>
    ): MutableMap<Group, List<Picture>> {
        val decadeGrouped = mutableMapOf<Group, List<Picture>>()

        nextGroups.forEach { decade ->
            val sizes = decade.value.mapNotNull { key -> grouped[key]?.size }

            val countOfTinyGroup = sizes.count { size -> size < MINIMAL_GROUP_SIZE }

            if (countOfTinyGroup > sizes.count() / 2) {
                val pictures = mutableListOf<Picture>()
                decade.value.forEach { key ->
                    pictures.addAll(grouped[key] ?: emptyList())
                }
                decadeGrouped[decade.key] = pictures
            } else {
                decade.value.forEach { key ->
                    decadeGrouped[key] = grouped[key] ?: emptyList()
                }
            }
        }
        return decadeGrouped
    }

    fun get(id: Id): Flow<Picture> {
        return localRepository.getPicture(id.date).filterNotNull()
    }

    companion object {
        private const val MINIMAL_GROUP_SIZE = 3
    }
}