package cn.putong.home.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.home.R
import cn.putong.home.mvp.data.model.NewThingsModel
import kotlinx.android.synthetic.main.item_newthings.view.*

/**
 * 新鲜事适配器
 * Created by xinyi on 2018/1/8.
 */
class NewThingsAdapter(
        private var mList: List<NewThingsModel.Posts> = listOf()) :
        RecyclerView.Adapter<NewThingsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            val mPost = mList[position]
            title.text = mPost.title
            author.text = mPost.author.nickname
            time.text = TimeUtil.format(TimeUtil.getDate(mPost.date))
            comments.text = mPost.comment_count.toString() +
                    context.resources.getString(R.string.newthings_comment_count_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).
                inflate(R.layout.item_newthings, parent, false))
    }

    override fun getItemCount() = mList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun updateList(mList: List<NewThingsModel.Posts>) {
        this.mList = mList
        notifyDataSetChanged()
    }
}