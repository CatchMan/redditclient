package com.catchman.redditclient.ui.main

import android.net.Uri
import android.os.Bundle
import com.catchman.redditclient.R
import android.support.customtabs.CustomTabsIntent
import com.catchman.data.ApiConst.Companion.BASE_URL
import com.catchman.redditclient.ui.base.BaseActivity
import com.catchman.redditclient.ui.listeners.OpenLinkListener
import com.catchman.redditclient.ui.topPosts.TopPostsFragment


class MainActivity : BaseActivity(), OpenLinkListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        initUI()


    }

    private fun initUI() {
        replaceFragment(TopPostsFragment.start(this), R.id.container, false, false)
    }

    override fun onOpenLink(url: String?) {
        if(url.isNullOrBlank()){
            return
        }
        val url = BASE_URL + url
        val builder = CustomTabsIntent.Builder()
// set toolbar color and/or setting custom actions before invoking build()
// Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        val customTabsIntent = builder.build()
// and launch the desired Url with CustomTabsIntent.launchUrl()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}
