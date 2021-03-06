package cn.putong.detail.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.helper.TimeHelper
import cn.putong.commonlibrary.mvp.detail.model.PostCommentModel
import cn.putong.detail.R
import kotlinx.android.synthetic.main.item_postcomment.view.*

/**
 * 新闻类型数据评论适配器
 * Created by xinyi on 2018/1/12.
 */
class PostCommentAdapter(
        private var mList: List<PostCommentModel.Post.Comment> = listOf()) :
        RecyclerView.Adapter<PostCommentAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder!!.itemView) {
            val mComment = mList[position]
            author.text = mComment.name
            positive_count.text = resources.getString(R.string.positive_symbol, mComment.vote_positive)
            negative_count.text = resources.getString(R.string.negative_symbol, mComment.vote_negative)
            time.text = TimeHelper.format(TimeHelper.getDate(mComment.date))
            content.text = Html.fromHtml(mComment.content).trim()
        }
    }

    override fun getItemCount() = mList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).
                inflate(R.layout.item_postcomment, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun updateList(mList: List<PostCommentModel.Post.Comment>) {
        this.mList = mList
        notifyDataSetChanged()
    }

}