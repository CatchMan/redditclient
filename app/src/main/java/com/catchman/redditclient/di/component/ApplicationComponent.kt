package com.catchman.redditclient.di.component

import android.app.Application
import android.content.Context
import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.catchman.domain.gateway.RedditGateway
import com.catchman.redditclient.RedditApp
import com.catchman.redditclient.di.ApplicationContext
import com.catchman.redditclient.di.modul.ApplicationModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by ujujzk on 19.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(app: RedditApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun redditGateway(): RedditGateway

}