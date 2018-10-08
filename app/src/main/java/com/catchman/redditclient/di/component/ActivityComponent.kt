package com.catchman.redditclient.di.component

import com.catchman.redditclient.di.modul.ActivityModule
import com.catchman.redditclient.di.PerActivity
import com.catchman.redditclient.ui.main.MainActivity
import dagger.Component


/**
 * Created by ujujzk on 19.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}