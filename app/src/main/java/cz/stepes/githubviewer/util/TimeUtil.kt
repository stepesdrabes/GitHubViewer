package cz.stepes.githubviewer.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun String.toAgo(): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        val date = parser.parse(this) ?: return ""

        return difference(date.time, System.currentTimeMillis())
    }

    private fun difference(startTime: Long, endTime: Long): String {
        var different = endTime - startTime

        val millisInSecond: Long = 1000
        val minutesInMilli = millisInSecond * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays = different / daysInMilli
        different %= daysInMilli
        val elapsedHours = different / hoursInMilli
        different %= hoursInMilli
        val elapsedMinutes = different / minutesInMilli

        if (elapsedDays > 0) {
            val dateFormat = SimpleDateFormat("dd. MM. yyyy", Locale.GERMAN)
            return dateFormat.format(startTime)
        }

        if (elapsedHours > 0) {
            return "${elapsedHours}h"
        }

        return "${elapsedMinutes}m"
    }
}