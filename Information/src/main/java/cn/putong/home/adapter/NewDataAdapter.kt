package cn.putong.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseRecyclerAdapter
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.home.R
import cn.putong.home.mvp.data.model.NewModel
import kotlinx.android.synthetic.main.item_new.view.*

/**
 * 普通类型item适配器
 * Created by xinyi on 2018/1/8.
 */
class NewDataAdapter(
        private var mList: ArrayList<NewModel.Post> = ArrayList(),
        private val onClickListener: (Int) -> Unit) :
        BaseRecyclerAdapter() {

    private var FOOTER: NewModel.Post = NewModel.Post()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is NormalViewHolder) {
            with(holder.itemView!!) {
                val mPost = mList[position]
                title.text = mPost.title
                author.text = mPost.author.nickname
                time.text = TimeUtil.format(TimeUtil.getDate(mPost.date))
                comments.text = mPost.comment_count.toString() + context.resources.getString(R.string.new_comment_count_text)
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
                NormalViewHolder(LayoutInflater.from(parent?.context).
                        inflate(R.layout.item_new, parent, false))

            else ->
                FooterViewHolder(LayoutInflater.from(parent?.context).
                        inflate(R.layout.item_recyclerview_footer, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val mPost = mList[position]
        return if (mPost.id == -1)
            TYPE_FOOTER
        else
            TYPE_NORMAL
    }

    fun updateList(mList: ArrayList<NewModel.Post>) {
        this.mList = mList
        notifyDataSetChanged()
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