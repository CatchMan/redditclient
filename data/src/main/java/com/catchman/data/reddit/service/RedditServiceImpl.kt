package com.catchman.data.reddit.service

import io.reactivex.Single
import javax.inject.Inject



class RedditServiceImpl @Inject constructor(val redditApi: RedditApi): RedditService {


    override fun getTop(limit: Int, after : String?): Single<Info> = this.redditApi.getTop(limit, after)

}