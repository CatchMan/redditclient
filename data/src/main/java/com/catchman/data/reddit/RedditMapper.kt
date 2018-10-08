package com.catchman.data.reddit

import com.catchman.data.reddit.service.Info
import com.catchman.domain.model.Data
import com.catchman.domain.model.DataHolder
import io.reactivex.Observable
import io.reactivex.functions.Function


class InfoFromServiceToPresentation : Function<Info, DataHolder> {
    override fun apply(t: Info): DataHolder {
        var list = Observable.fromIterable(t.data!!.children)
                .map { Data(it.data?.title, it.data?.thumbnail, it.data?.author,
                        it.data?.authorFullname, it.data?.subreddit, it.data?.numComments,
                        it.data?.score, it.data?.created, it.data?.permalink) }
                .toList()
                .blockingGet()
        return DataHolder(list, t.data?.after)
    }

}