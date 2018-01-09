package cn.putong.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.home.R
import cn.putong.home.mvp.data.model.BoringPicturesModel
import kotlinx.android.synthetic.main.item_boringpictures.view.*

/**
 * 无聊图列表适配器
 * Created by lala on 2018/1/8.
 */
class BoringPicturesAdapter(private var mList: ArrayList<BoringPicturesModel.Comment> = ArrayList())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 新鲜事
    private val TYPE_BORINGPICTURES = 1

    // 底部视图
    private val TYPE_FOOTER = 2

    private var FOOTER: BoringPicturesModel.Comment = BoringPicturesModel.Comment()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_BORINGPICTURES ->
                BoringPicturesViewHolder(LayoutInflater.from(parent?.context).
                        inflate(R.layout.item_boringpictures, parent, false))

            else ->
                NewThingsAdapter.FooterViewHolder(LayoutInflater.from(parent?.context).
                        inflate(R.layout.item_recyclerview_footer, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is BoringPicturesViewHolder)
            with(holder.itemView!!) {
                val mComment = mList[position]
                author.text = mComment.comment_author
                time.text = TimeUtil.format(TimeUtil.getDate(mComment.comment_date))
            }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        val mComment = mList[position]
        return if (mComment.comment_ID.isEmpty())
            TYPE_FOOTER
        else
            TYPE_BORINGPICTURES
    }

    fun updateList(mList: ArrayList<BoringPicturesModel.Comment>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    fun addFooter() {
        mList.add(FOOTER)
        notifyItemInserted(mList.size - 1)
    }

    fun removeFooter() {
        mList.removeAt(mList.size - 1)
        notifyItemRemoved(mList.size)
    }

    // 无聊图ViewHolder
    class BoringPicturesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // 底部ViewHolder
    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)

}