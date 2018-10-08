package com.catchman.data.reddit

import com.catchman.data.DataLogger
import com.catchman.data.reddit.service.RedditService
import com.catchman.domain.gateway.RedditGateway
import com.catchman.domain.model.DataHolder
import io.reactivex.Single
import javax.inject.Inject



class RedditGatewayImpl @Inject constructor(val service: RedditService, val logger: DataLogger) : RedditGateway {
    override fun getTop(limit: Int, after: String?): Single<DataHolder> =
            service.getTop(limit, after)
                    .map(InfoFromServiceToPresentation())
}