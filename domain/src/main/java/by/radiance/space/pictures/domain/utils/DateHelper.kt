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

    fun tomorrow(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.time
    }
}