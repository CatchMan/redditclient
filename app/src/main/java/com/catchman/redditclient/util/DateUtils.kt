package com.catchman.redditclient.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*




fun getHourPostCreated(time: Long?) : String {
    if(time == null){
        return ""
    }
    var currentDate = Date()
    val date = Date(time * 1000)
    return ((currentDate.time -date.time)/(3600*1000)).toString()
}
