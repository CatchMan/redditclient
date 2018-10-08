package com.catchman.data.reddit.service

import io.reactivex.Single


/**
 * Created by ujujzk on 21.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

interface RedditService {

    fun getTop(limit: Int, after : String?):Single<Info>

}