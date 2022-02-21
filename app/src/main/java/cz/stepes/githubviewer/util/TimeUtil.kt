package cz.stepes.githubviewer.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun Instant.toAgo(): String {
        val currentMoment = Clock.System.now()
        val duration = currentMoment - this

        if (duration.inWholeDays > 0) {
            val simpleDateFormat = SimpleDateFormat("dd. MM. yyyy", Locale.GERMAN)
            return simpleDateFormat.format(toEpochMilliseconds())
        }

        if (duration.inWholeHours > 0) {
            return "${duration.inWholeHours}h"
        }

        return "${duration.inWholeMinutes}m"
    }
}