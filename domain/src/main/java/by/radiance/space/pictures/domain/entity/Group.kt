package by.radiance.space.pictures.domain.entity

import by.radiance.space.pictures.domain.utils.DateHelper
import by.radiance.space.pictures.domain.utils.DateHelper.MONTH_FORMAT
import by.radiance.space.pictures.domain.utils.DateHelper.YEAR_FORMAT
import java.util.*

sealed class Group(val name: String) {
    data class Month(val date: Date): Group(DateHelper.getDate(date, MONTH_FORMAT))
    data class Year(val date: Date): Group(DateHelper.getDate(date, YEAR_FORMAT))
    data class Decade(val date: Date): Group("${DateHelper.getDate(date, YEAR_FORMAT)}s")
}