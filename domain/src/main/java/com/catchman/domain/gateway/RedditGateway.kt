package com.catchman.domain.gateway

import com.catchman.domain.model.*
import io.reactivex.Single



interface RedditGateway {

    fun getTop(limit : Int, after : String?) : Single<DataHolder>

}