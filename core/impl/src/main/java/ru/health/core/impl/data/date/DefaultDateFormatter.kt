package ru.health.core.impl.data.date

import ru.health.core.api.data.date.DateFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject
import kotlin.concurrent.getOrSet

class DefaultDateFormatter @Inject constructor() : DateFormatter {

    private val simpleRequestDateFormatThreadLocal = ThreadLocal<SimpleDateFormat>()
    private val simpleFromRequestDateFormatThreadLocal = ThreadLocal<SimpleDateFormat>()
    private val simpleDateFormatThreadLocal = ThreadLocal<SimpleDateFormat>()

    private val simpleRequestDateFormatter
        get() = simpleRequestDateFormatThreadLocal.getOrSet {
            SimpleDateFormat(DateFormatter.Companion.TO_REQUEST_DATE_FORMAT, Locale("ru")).apply {
                timeZone = TimeZone.getDefault()
            }
        }

    private val simpleFromRequestDateFormatter
        get() = simpleFromRequestDateFormatThreadLocal.getOrSet {
            SimpleDateFormat(DateFormatter.Companion.FROM_REQUEST_DATE_FORMAT, Locale("ru")).apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }
        }

    private val simpleDateFormatter
        get() = simpleDateFormatThreadLocal.getOrSet {
            SimpleDateFormat(DateFormatter.Companion.PRESENTATION_DATE_FORMAT, Locale("ru")).apply {
                timeZone = TimeZone.getDefault()
            }
        }

    override fun fromRequestDate(date: String): String? {
        return simpleFromRequestDateFormatter.parse(date)?.let { requestDate ->
            simpleDateFormatter.format(requestDate)
        }
    }

    override fun toRequestDate(date: String): String? {
        return simpleDateFormatter.parse(date)?.let { requestDate ->
            simpleRequestDateFormatter.format(requestDate)
        }
    }

    override fun formatDateToRequestString(date: Date): String {
        return simpleRequestDateFormatter.format(date)
    }

    override fun formatDate(date: Date, format: String): String {
        return SimpleDateFormat(format, Locale("ru")).format(date)
    }

    override fun formatToDate(date: String, format: String): Date? {
        val dateFromFormat = SimpleDateFormat(format, Locale("ru")).apply {
            timeZone = TimeZone.getTimeZone("GMT")
        }.parse(date)
        return dateFromFormat
    }

    override fun formatToDateWithCurrentTime(date: String, format: String): Date? {
        return formatToDate(date, format)?.let { addCurrentTimeToDate(it) }
    }

    private fun addCurrentTimeToDate(date: Date): Date {
        val requiredDate = Calendar.getInstance().apply {
            time = date
        }
        val requiredTime = Calendar.getInstance()
        requiredDate.apply {
            set(Calendar.HOUR_OF_DAY, requiredTime.get(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, requiredTime.get(Calendar.MINUTE))
            set(Calendar.SECOND, requiredTime.get(Calendar.SECOND))
            set(Calendar.MILLISECOND, requiredTime.get(Calendar.MILLISECOND))
        }
        return requiredDate.time
    }
}