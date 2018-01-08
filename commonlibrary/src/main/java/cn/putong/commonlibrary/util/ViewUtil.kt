package cn.putong.commonlibrary.util

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import cn.putong.commonlibrary.R
import com.scwang.smartrefresh.header.MaterialHeader

/**
 * 控件工具类
 * 常用控件的基本设置,本项目中用到较少，所以所有控件的设置放在一个文件里
 * Created by xinyi on 2018/1/8.
 */

/**
 * 设置RecyclerView默认分割线
 */
fun RecyclerView.setDefaultDivider(context: Context) {
    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
}

/**
 * 设置SwipeRefreshLayout颜色
 */
fun MaterialHeader.setColor(resid: Int = R.color.colorAccent) {
    setColorSchemeColors(resources.getColor(resid))
}