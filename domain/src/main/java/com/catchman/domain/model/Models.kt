package com.catchman.domain.model


/**
 * Created by ujujzk on 20.07.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

data class DataHolder(
        var datas: List<Data>?,
        var after: String?
)

data class Data(
        var title : String?,
        var thumbnail : String?,
        var author : String?,
        var authorFullname : String?,
        var subreddit : String?,
        var numberComments : Int?,
        var score : Int?,
        var created : Long?,
        var linkToPost : String?
)