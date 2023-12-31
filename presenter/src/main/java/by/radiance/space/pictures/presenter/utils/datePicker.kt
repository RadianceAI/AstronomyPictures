package by.radiance.space.pictures.presenter.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.util.Calendar
import java.util.Date

@Deprecated("use compose date picker")
fun showDatePicker(
    context: Context,
    onDateSelected: (Date) -> Unit,
    maxDate: Long,
    minDate: Long,
) {
    val today = Calendar.getInstance()
    today.timeInMillis = maxDate

    val year = today[Calendar.YEAR]
    val month = today[Calendar.MONTH]
    val dayOfMonth = today[Calendar.DAY_OF_MONTH]

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            val calendar = Calendar.getInstance()
            calendar.set(selectedYear, selectedMonth, selectedDay)
            onDateSelected(calendar.time)
        },
        year,
        month,
        dayOfMonth,
    )

    datePicker.datePicker.minDate = minDate
    datePicker.datePicker.maxDate = maxDate
    datePicker.show()
}