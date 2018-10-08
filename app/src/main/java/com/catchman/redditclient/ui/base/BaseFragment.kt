package com.catchman.redditclient.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import com.catchman.redditclient.RedditApp
import com.catchman.redditclient.di.component.DaggerFragmentComponent
import com.catchman.redditclient.di.component.FragmentComponent
import com.catchman.redditclient.di.modul.ActivityModule
import com.catchman.redditclient.di.modul.FragmentModule


/**
 * Created by ujujzk on 17.07.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

abstract class BaseFragment : Fragment(), IView {

    protected lateinit var fragmentComponent: FragmentComponent

    var activity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity){
            this.activity = context
            fragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(FragmentModule())
                    .activityModule(ActivityModule(context))
                    .applicationComponent((context.application as RedditApp).appComponent)
                    .build()
            context.onFragmentAttached()
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun showProgress(show: Boolean) {
        if (isAdded) activity?.showProgress(show)
    }

    override fun isNetworkConnected(): Boolean = activity?.isNetworkConnected() ?: false

    override fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}