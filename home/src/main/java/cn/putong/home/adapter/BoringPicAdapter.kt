package cn.putong.home.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseRecyclerAdapter
import cn.putong.commonlibrary.util.FrescoUtil
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.home.R
import cn.putong.home.mvp.data.model.BoringPicturesModel
import kotlinx.android.synthetic.main.view_boringpic_content.view.*

/**
 * 无聊图列表适配器
 * Created by lala on 2018/1/8.
 */
class BoringPicAdapter(private var mList: ArrayList<BoringPicturesModel.Comment> = ArrayList())
    : BaseRecyclerAdapter() {

    private var FOOTER: BoringPicturesModel.Comment = BoringPicturesModel.Comment()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val attachToRoot = false
        return when (viewType) {
            TYPE_NORMAL ->
                BoringPicturesViewHolder(LayoutInflater.from(parent?.context).
                        inflate(R.layout.item_boringpic, parent, attachToRoot))
            else ->
                NewThingsAdapter.FooterViewHolder(LayoutInflater.from(parent?.context).
                        inflate(R.layout.item_recyclerview_footer, parent, attachToRoot))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is BoringPicturesViewHolder)
            with(holder.itemView!!) {
                val mComment = mList[position]

                author.text = mComment.comment_author
                time.text = TimeUtil.format(TimeUtil.getDate(mComment.comment_date))
                content.text = mComment.text_content.trim()
                content.visibility = if (mComment.text_content.trim().isEmpty()) View.GONE else View.VISIBLE

                // 目前仅支持单图显示
                FrescoUtil.setAnimatorController(Uri.parse(mComment.pics[0]), img)

                positive_count.text = context.resources.getString(R.string.boringpic_content_positive_symbol) + mComment.vote_positive
                negative_count.text = context.resources.getString(R.string.boringpic_content_negative_symbol) + mComment.vote_positive
                comment_count.text = resources.getString(R.string.boringpic_content_comment_count_text) + mComment.sub_comment_count
            }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return if (mList[position].comment_ID.isEmpty())
            TYPE_FOOTER
        else
            TYPE_NORMAL
    }

    fun updateList(mList: ArrayList<BoringPicturesModel.Comment>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun addFooter() {
        mList.add(FOOTER)
        notifyItemInserted(mList.size - 1)
    }

    override fun removeFooter() {
        mList.removeAt(mList.size - 1)
        notifyItemRemoved(mList.size)
    }

    class BoringPicturesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)

}