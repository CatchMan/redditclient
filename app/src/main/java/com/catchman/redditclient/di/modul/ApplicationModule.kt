package com.catchman.redditclient.di.modul

import android.app.Application
import android.content.Context
import com.catchman.data.ApiConst
import com.catchman.data.DataLogger
import com.catchman.domain.DomainLogger
import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.catchman.redditclient.UiThread
//import com.sfera.mirror.UiThread
import com.catchman.redditclient.di.ApplicationContext
import com.catchman.redditclient.util.domainLogger
import com.catchman.data.JobExecutor
import com.catchman.data.interceptor.LoggingInterceptor
import com.catchman.data.reddit.RedditGatewayImpl
import com.catchman.data.reddit.service.RedditApi
import com.catchman.data.reddit.service.RedditService
import com.catchman.data.reddit.service.RedditServiceImpl
import com.catchman.domain.gateway.RedditGateway
import com.catchman.redditclient.RedditApp
import com.catchman.redditclient.util.dataLogger
import com.catchman.redditclient.util.netLogger
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by ujujzk on 19.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = app

    @Provides
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    fun provideDataLogger(): DataLogger = dataLogger()

    @Provides
    fun provideDomainLogger(): DomainLogger = domainLogger()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = LoggingInterceptor(object : LoggingInterceptor.Logger {
            override fun log(message: String) = netLogger(message)
        }).setLevel(LoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(ChuckInterceptor(RedditApp.context))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideSferaApi(client: OkHttpClient): RedditApi {
        return Retrofit.Builder()
                .baseUrl(ApiConst.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RedditApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSferaService(redditService: RedditServiceImpl): RedditService = redditService

    @Provides
    @Singleton
    fun provideRedditGateway(redditGateway: RedditGatewayImpl): RedditGateway = redditGateway

}