package com.catchman.redditclient.ui.listeners

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import com.catchman.redditclient.util.loge


abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 10 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 0 // The minimum amount of items to have below your current scroll position before loading more.
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        (visibleItemCount.toString() + " = " + totalItemCount + " = " + firstVisibleItem).loge()
        if (loading) {
            if (totalItemCount > previousTotal && totalItemCount - 3 <= firstVisibleItem) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount >= visibleThreshold - firstVisibleItem) {
            onLoadMore()
            loading = true
        }
    }

    abstract fun onLoadMore()

}
