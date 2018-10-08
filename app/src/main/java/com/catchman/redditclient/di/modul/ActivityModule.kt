package com.catchman.redditclient.di.modul

import android.support.v7.app.AppCompatActivity
import com.catchman.redditclient.di.ActivityContext
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by ujujzk on 20.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext() = activity

    @Provides
    fun provideActivity() = activity

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}