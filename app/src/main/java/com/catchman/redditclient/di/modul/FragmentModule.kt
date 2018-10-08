package com.catchman.redditclient.di.modul

import com.catchman.redditclient.di.PerFragment
import com.catchman.redditclient.ui.topPosts.TopPostsContract
import com.catchman.redditclient.ui.topPosts.TopPostsFragment
import com.catchman.redditclient.ui.topPosts.TopPostsPresenter
import dagger.Module
import dagger.Provides


/**
 * Created by ujujzk on 17.07.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@Module
class FragmentModule {

    @Provides
    @PerFragment
    fun provideTopPostsPresenter (presenter: TopPostsPresenter<TopPostsContract.View>) : TopPostsContract.Presenter<TopPostsContract.View> = presenter

}