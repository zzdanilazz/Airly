package ru.health.core.api.data.date

import java.util.Date

interface DateFormatter {

    fun toRequestDate(date: String): String?

    fun fromRequestDate(date: String): String?

    fun formatDateToRequestString(date: Date): String

    fun formatDate(date: Date, format: String = PRESENTATION_DATE_FORMAT): String

    fun formatToDate(date: String, format: String = PRESENTATION_DATE_FORMAT): Date?

    fun formatToDateWithCurrentTime(date: String, format: String = PRESENTATION_DATE_FORMAT): Date?

    companion object {
        const val PRESENTATION_DATE_FORMAT = "dd.MM.yyyy"
        const val LAST_ACTION_DATE_FORMAT = "dd.MM.yyyy 'в' HH:mm"
        const val TO_REQUEST_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ"
        const val FROM_REQUEST_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val FULL_DATE_FORMAT = "d MMMM yyyy"
    }
}