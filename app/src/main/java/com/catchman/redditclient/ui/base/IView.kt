package com.catchman.redditclient.ui.base


/**
 * Created by ujujzk on 17.07.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

interface IView {

    fun isNetworkConnected() : Boolean

    fun toast(message: String)

    fun showProgress(show: Boolean){
        throw RuntimeException("Not implemented")
    }
}