package cn.putong.commonlibrary.util

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView

/**
 * RecyclerView工具类
 * Created by xinyi on 2018/1/8.
 */

/**
 * 设置RecyclerView默认分割线
 */
fun RecyclerView.setDefaultDivider(context: Context) {
    this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
}