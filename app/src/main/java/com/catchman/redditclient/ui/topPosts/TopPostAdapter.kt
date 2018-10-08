package com.catchman.redditclient.ui.topPosts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.catchman.domain.model.Data
import com.catchman.redditclient.R
import com.catchman.redditclient.util.getHourPostCreated
import com.catchman.redditclient.util.loge
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class TopPostAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val FOOTER_VIEW = 1
    private var listener : OnItemClickListener ?= null
    private var datas : ArrayList<Data> ?= null

    constructor(listener: OnItemClickListener) : this() {
        this.listener = listener
        datas = ArrayList()
    }

    interface OnItemClickListener {
        fun OnItemClick(url : String?)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        viewType.toString().loge()
        if (viewType != FOOTER_VIEW) {
            return TopPostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
        } else {
            return FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false))
        }
    }

    override fun getItemCount(): Int {
        if (datas == null) {
            return 0;
        }
        // Add extra view to show the footer view
        return datas!!.size + 1;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is TopPostsViewHolder) {
            initNormalHolder(holder, datas!![position])
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == datas?.size) {
            // This is where we'll add footer.
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    fun initNormalHolder(holder: TopPostsViewHolder, data: Data){
        holder.tvTitle.text = data.title
        holder.tvAuthor.text = data.author + " (" + data.authorFullname + ") " + data.subreddit
        holder.tvRatting.text = data.score.toString()
        holder.tvNumberOfComments.text = data.numberComments.toString()
        holder.tvPostDate.text = getHourPostCreated(data.created) + " hour ago"
        Glide.with(holder.ivImage.context)
                .load(data.thumbnail)
                .into(holder.ivImage);
        holder.itemView.setOnClickListener {
            listener?.OnItemClick(data.linkToPost)
        }
    }

    fun addPosts(datas: List<Data>){
        this.datas?.addAll(datas)
        notifyDataSetChanged()
    }


    inner class TopPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.tvTitle
        var ivImage = itemView.ivImage
        var tvAuthor = itemView.tvAuthor
        var tvRatting = itemView.tvRatting
        var tvNumberOfComments = itemView.tvNumberOfComments
        var tvPostDate = itemView.tvPostDate
    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progress = itemView.progress
    }


}