package com.catchman.redditclient.ui.topPosts

import android.provider.ContactsContract
import com.catchman.domain.interactor.reddit.GetTopPostsUseCase
import com.catchman.domain.model.Data
import com.catchman.domain.model.DataHolder
import com.catchman.redditclient.ui.base.BasePresenter
import com.catchman.redditclient.util.loge
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class TopPostsPresenter<V : TopPostsContract.View> @Inject constructor(val getTopPostsUseCase: GetTopPostsUseCase,
                                                                       compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(compositeDisposable), TopPostsContract.Presenter<V>{


    override fun getData(limit: Int, after: String?) {
        getTopPostsUseCase.execute(object : DisposableSingleObserver<DataHolder>(){
            override fun onSuccess(t: DataHolder) {
                view?.loadDataSuccess(t)
            }

            override fun onError(e: Throwable) {
                e.message?.loge()
                view?.loadDataFail()
            }
        }, GetTopPostsUseCase.Params(limit, after))
    }
}