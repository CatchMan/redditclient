package com.catchman.redditclient.ui.base


/**
 * Created by ujujzk on 17.07.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

interface IPresenter<V : IView> {

    fun bindView(view: V)

    fun unbindView()

    fun dispose()
}