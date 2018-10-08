package com.catchman.redditclient.di.component

import com.catchman.redditclient.di.modul.ActivityModule
import com.catchman.redditclient.di.PerFragment
import com.catchman.redditclient.ui.topPosts.TopPostsFragment
import com.catchman.redditclient.di.modul.FragmentModule
import dagger.Component


/**
 * Created by ujujzk on 17.07.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@PerFragment
@Component(dependencies = [(ApplicationComponent::class)], modules = [(FragmentModule::class),(ActivityModule::class)])
interface FragmentComponent {

    fun inject(topPostsFragment: TopPostsFragment)
//
//    fun inject(atmoFragment: AtmoFragment)
//
//    fun inject(leisureFragment: LeisureFragment)
}