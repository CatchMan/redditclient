package com.catchman.redditclient.ui.listeners

import android.os.Parcelable
import java.io.Serializable

interface OpenLinkListener : Serializable{

    fun onOpenLink(url: String?)
}