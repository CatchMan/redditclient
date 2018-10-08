package com.catchman.data.reddit.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query



interface RedditApi {

    @GET("r/all/top.json")
    fun getTop(
        @Query("limit") limit: Int,
        @Query("after") after: String?
    ) : Single<Info>


}