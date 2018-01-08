package cn.putong.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.home.R
import cn.putong.home.mvp.data.model.BoringPicturesModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_boringpictures.view.*

/**
 * 无聊图列表适配器
 * Created by lala on 2018/1/8.
 */
class BoringPicturesAdapter(private var mList: List<BoringPicturesModel.Comment> = listOf())
    : RecyclerView.Adapter<BoringPicturesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).
                    inflate(R.layout.item_boringpictures, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            val mComment = mList[position]
            author.text = mComment.comment_author
            time.text = TimeUtil.format(TimeUtil.getDate(mComment.comment_date))
            Glide.with(context).load(mComment.pics[0]).into(img)
        }
    }

    override fun getItemCount() = mList.size

    fun updateList(mList: List<BoringPicturesModel.Comment>) {
        this.mList = mList
        notifyDataSetChanged()
    }

}