package com.catchman.domain.interactor.reddit

import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.catchman.domain.gateway.RedditGateway
import com.catchman.domain.interactor.UseCase
import com.catchman.domain.model.Data
import com.catchman.domain.model.DataHolder
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetTopPostsUseCase @Inject constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        compositeDisposable: CompositeDisposable,
        private val redditGateway: RedditGateway) : UseCase<DataHolder, GetTopPostsUseCase.Params>(threadExecutor, postExecutionThread, compositeDisposable) {

    override fun buildUseCaseObservable(params: Params): Single<DataHolder> = redditGateway.getTop(params.limit, params.after)

    class Params(
            var limit : Int,
            var after : String?
    )

}