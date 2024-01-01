package by.radiance.space.pictures.domain.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.math.abs

object DateUtil {
    private const val ID_DATE_FORMAT = "yyyy-MM-dd"
    private const val CARD_DATE_FORMAT = "MM.dd"

    const val MILLISECONDS_IN_DAY = 86400000L

    fun getDate(date: Date, format: String = CARD_DATE_FORMAT): String {
        return SimpleDateFormat(format).format(date)
    }

    fun parseId(string: String): Date? {
        return SimpleDateFormat(ID_DATE_FORMAT).parse(string)
    }

    fun formatId(date: Date): String {
        return SimpleDateFormat(ID_DATE_FORMAT).format(date)
    }

    fun tomorrow(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.time
    }

    fun APODStartDate(): Date {
        val startDate = Calendar.getInstance()
        startDate.set(1995, 5, 16)

        return startDate.time
    }

    fun daysBetweenDates(start: Date, end: Date): Int {
        return abs((start.time - end.time) / MILLISECONDS_IN_DAY).toInt()
    }
}

fun Date.plusDays(days: Int) : Date {
    val newTime = this.time + (days * DateUtil.MILLISECONDS_IN_DAY)
    return Date(newTime)
}

fun Date.minusDays(days: Int) : Date {
    val newTime = this.time - (days * DateUtil.MILLISECONDS_IN_DAY)
    return Date(newTime)
}
