package cn.putong.commonlibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import cn.putong.commonlibrary.R
import com.facebook.drawee.view.SimpleDraweeView
import android.widget.RelativeLayout
import android.widget.TextView



/**
 * 图片网格适配器
 * Created by xinyi on 2018/1/26.
 */
class PicGirdAdapter(private var mPics: ArrayList<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pic, parent, false)
            holder = ViewHolder()
            holder.pic = view.findViewById(R.id.pic) as SimpleDraweeView
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder

            if (holder.pic.tag != null) {
                val tagValue = holder.pic.tag.toString()
                if (tagValue == mPics[position])
                    return view
            }
        }

        holder.pic.setImageURI(mPics[position])
        holder.pic.tag = mPics[position]
        return view
    }

    override fun getItem(position: Int) = mPics[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = mPics.size

    fun updateList(mPics: ArrayList<String>) {
        this.mPics = mPics
        // notifyDataSetChanged()
    }

    class ViewHolder {
        lateinit var pic: SimpleDraweeView
    }


}