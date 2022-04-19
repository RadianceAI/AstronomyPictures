package by.radiance.space.pictures.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    const val CARD_DATE_FORMAT = "MMM dd, yyyy"
    const val MONTH_FORMAT = "YYYY MMMM"
    const val YEAR_FORMAT = "YYYY"

    fun getDate(date: Date, format: String = CARD_DATE_FORMAT): String {
        return SimpleDateFormat(format).format(date)
    }
}