package com.catchman.redditclient.ui.topPosts

import com.catchman.domain.model.Data
import com.catchman.domain.model.DataHolder
import com.catchman.redditclient.ui.base.IPresenter
import com.catchman.redditclient.ui.base.IView

class TopPostsContract {

    interface View : IView {

        fun loadDataSuccess(datas: DataHolder)

        fun loadDataFail()

    }

    interface Presenter<V : View> : IPresenter<V> {

        fun getData(limit : Int, after : String?)
    }
}