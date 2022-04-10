package by.radiance.space.picrures.presenter.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    const val CARD_DATE_FORMAT = "MMM dd, yyyy"

    fun getDate(date: Date, format: String = CARD_DATE_FORMAT): String {
        return SimpleDateFormat(format).format(date)
    }
}