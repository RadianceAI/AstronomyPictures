package by.radiance.space.pictures.data.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun getDate(date: Date): String {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        return simpleDate.format(date)
    }

    fun getDate(string: String): Date {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        return simpleDate.parse(string)
    }
}