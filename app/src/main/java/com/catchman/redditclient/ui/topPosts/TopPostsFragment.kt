package com.catchman.redditclient.ui.topPosts

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.catchman.domain.model.DataHolder

import com.catchman.redditclient.R
import com.catchman.redditclient.ui.base.BaseFragment
import com.catchman.redditclient.ui.listeners.OpenLinkListener
import kotlinx.android.synthetic.main.fragment_top_posts.*
import javax.inject.Inject
import com.catchman.redditclient.ui.listeners.EndlessRecyclerOnScrollListener


class TopPostsFragment : BaseFragment(), TopPostsContract.View, TopPostAdapter.OnItemClickListener {

    companion object {
        private val EXTRA_LISTENER = "extraListener"

        fun start(listener: OpenLinkListener) : TopPostsFragment {
            var fragment = TopPostsFragment()
            val args = Bundle()
            args.putSerializable(EXTRA_LISTENER, listener)
            fragment.arguments = args
            return fragment
        }
    }

    private val LIMITE_CONST = 10
    private var adapter : TopPostAdapter? = null
    private var after : String? = null
    private var listener : OpenLinkListener? = null
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var layoutManager: LinearLayoutManager? = null

    @Inject
    lateinit var presenter: TopPostsContract.Presenter<TopPostsContract.View>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentComponent.inject(this)
        presenter.bindView(this)
        return inflater.inflate(R.layout.fragment_top_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        presenter.getData(LIMITE_CONST, after)
        setUpViews()
    }

    private fun setUpViews(){
        if (arguments != null) {
            listener = arguments!!.getSerializable(EXTRA_LISTENER) as OpenLinkListener?
        }
    }

    private fun initUI() {
        layoutManager = LinearLayoutManager(context)
        adapter = TopPostAdapter(this)
        rvPostList.layoutManager = layoutManager
        rvPostList.adapter = adapter
        rvPostList.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager!!) {
            override fun onLoadMore() {
                presenter.getData(LIMITE_CONST, after)
            }

        })
    }

    private fun loadMoreItems() {
        presenter.getData(LIMITE_CONST, after)
        isLoading = true
    }

    override fun loadDataSuccess(datas: DataHolder) {
        adapter?.addPosts(datas.datas ?: ArrayList())
        after = datas.after
        isLoading = false
    }

    override fun loadDataFail() {
        toast(getString(R.string.cant_load_data))
        isLoading = false
    }

    override fun OnItemClick(url: String?) {
        listener?.onOpenLink(url)
    }
}
