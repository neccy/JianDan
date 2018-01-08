package cn.putong.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.home.R
import cn.putong.home.mvp.data.model.BoringPicturesModel

/**
 * 无聊图列表适配器
 * Created by lala on 2018/1/8.
 */
class BoringPicturesAdapter(var mList: List<BoringPicturesModel.Comment> = listOf())
    : RecyclerView.Adapter<BoringPicturesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).
                    inflate(R.layout.item_boringpictures, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun getItemCount() = mList.size

    fun updateList(mList: List<BoringPicturesModel.Comment>) {
        this.mList = mList
        notifyDataSetChanged()
    }

}