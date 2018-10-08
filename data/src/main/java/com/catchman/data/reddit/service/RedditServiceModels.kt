package com.catchman.data.reddit.service

import com.google.gson.annotations.SerializedName




data class Info(
        var data: DataHolder? = null
)

data class Child(
        var data: Data? = null
)

data class DataHolder(
        var children: List<Child>? = null,
        @SerializedName("after")
        var after: String? = null,
        @SerializedName("before")
        var before: String? = null
)

class Data (
        var subreddit: String? = null,
        @SerializedName("author_fullname")
        var authorFullname: String? = null,
        var title: String? = null,
        var name: String? = null,
        var domain: String? = null,
        var score: Int? = null,
        var thumbnail: String? = null,
        var created: Long? = null,
        var id: String? = null,
        var author: String? = null,
        @SerializedName("num_comments")
        var numComments: Int? = null,
        @SerializedName("permalink")
        var permalink: String? = null
)

