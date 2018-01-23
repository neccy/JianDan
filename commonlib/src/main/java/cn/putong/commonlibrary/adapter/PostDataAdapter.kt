package cn.putong.commonlibrary.adapter

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.R
import cn.putong.commonlibrary.base.BaseRecyclerAdapter
import cn.putong.commonlibrary.helper.TimeHelper
import cn.putong.commonlibrary.mvp.home.model.PostModel
import cn.putong.commonlibrary.realm.AppDB
import kotlinx.android.synthetic.main.item_post.view.*
import org.jetbrains.anko.textColor

/**
 * 普通类型item适配器
 * Created by xinyi on 2018/1/8.
 */
class PostDataAdapter(
        private var mList: ArrayList<PostModel.Post> = ArrayList(),
        private val onClickListener: (Int) -> Unit) :
        BaseRecyclerAdapter() {

    private var FOOTER: PostModel.Post = PostModel.Post()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        if (payloads!!.isEmpty())
            onBindViewHolder(holder, position)
        else
            if (holder is NormalViewHolder) {
                with(holder.itemView!!) {
                    val mPost = mList[position]
                    val titleColor = if (!mPost.have_seen)
                        ContextCompat.getColor(context, R.color.textview_black_color)
                    else
                        Color.GRAY
                    title.textColor = titleColor
                }
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is NormalViewHolder) {
            with(holder.itemView!!) {
                val mPost = mList[position]

                val titleColor = if (!mPost.have_seen)
                    ContextCompat.getColor(context, R.color.textview_black_color)
                else
                    Color.GRAY
                title.textColor = titleColor
                title.text = mPost.title

                author.text = mPost.author.nickname
                time.text = TimeHelper.format(TimeHelper.getDate(mPost.date))

                comments.text = resources.getString(R.string.comment_count_text, mPost.comment_count)

                img.setImageURI(mPost.custom_fields.thumb_c[0])
                item_main.setOnClickListener {
                    onClickListener.invoke(position)
                }
            }
        }
    }

    override fun getItemCount() = mList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_NORMAL ->
                NormalViewHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.item_post, parent, false))
            else ->
                FooterViewHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.item_recyclerview_footer, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val mPost = mList[position]
        return if (mPost.id == -1)
            TYPE_FOOTER
        else
            TYPE_NORMAL
    }

    fun updateList(mList: ArrayList<PostModel.Post>) {
        mList.setHaveSeenStatus()
        this.mList = mList
        notifyDataSetChanged()
    }

    /**
     * 设置已看状态
     */
    private fun List<PostModel.Post>.setHaveSeenStatus() {
        filter { AppDB.getHaveSeeRecord(it.id) != null }.forEach { it.have_seen = true }
    }

    override fun addFooter() {
        // 设置标识用来识别底部
        FOOTER.id = -1
        mList.add(FOOTER)
        notifyItemInserted(mList.size - 1)
    }

    override fun removeFooter() {
        mList.removeAt(mList.size - 1)
        notifyItemRemoved(mList.size)
    }

    // 新鲜事ViewHolder
    class NormalViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // 底部ViewHolder
    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

