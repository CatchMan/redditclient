package com.catchman.redditclient.util

import com.catchman.domain.DomainLogger
import com.orhanobut.logger.Logger
import com.catchman.data.DataLogger


/**
 * Created by ujujzk on 20.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

private const val PRESENTATION = "PRESENTATION"
private const val DATA = "DATA"
private const val NETWORK = "NETWORK"

fun String.logi() = Logger.t(PRESENTATION).i(this)

fun String.logd() = Logger.t(PRESENTATION).d(this)

fun String.logw() = Logger.t(PRESENTATION).w(this)

fun String.loge() = Logger.t(PRESENTATION).e(this)

fun String.loge(t: Throwable) = Logger.t(PRESENTATION).e(t, this)

fun dataLogger() : DataLogger = object : DataLogger {
        override fun log(message: String) = Logger.t(DATA).i(message)
}

fun domainLogger() : DomainLogger = object : DomainLogger {
    override fun log(message: String) = Logger.t(DATA).i(message)
}


fun netLogger(message: String){
    Logger.t(NETWORK).i(message)
}









