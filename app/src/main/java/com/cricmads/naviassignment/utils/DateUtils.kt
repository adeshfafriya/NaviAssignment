package com.cricmads.naviassignment.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getFormattedDate(dateStr: String): String{
        val str = if (dateStr.contains("Z")) dateStr.replace("Z", "") else dateStr
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("MMM dd, yyyy HH:mm a", Locale.US)
        outputFormat.timeZone = TimeZone.getDefault()
        val date: Date = inputFormat.parse(str) ?: return ""
        return outputFormat.format(date)
    }
}