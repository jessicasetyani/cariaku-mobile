package com.styletheory.cariaku.android.util

import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateFormatUtil {

    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    fun formatTimeAgo(time: LocalTime): String {
        val currentTime = LocalTime.now()
        val duration = Duration.between(time, currentTime)

        val seconds = duration.seconds
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            days > 0 -> "${days} hari yang lalu"
            hours > 0 -> "${hours} jam yang lalu"
            minutes > 0 -> "${minutes} menit yang lalu"
            seconds > 0 -> "${seconds} detik yang lalu"
            else -> "baru saja"
        }
    }
}
