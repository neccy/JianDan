package cn.putong.commonlibrary.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Base RecyclerView Adapter
 * Created by xinyi on 2018/1/9.
 */
abstract class BaseRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open fun addFooter() {

    }

    open fun removeFooter() {

    }
}