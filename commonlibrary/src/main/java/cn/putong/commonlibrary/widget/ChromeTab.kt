package cn.putong.commonlibrary.widget

import android.content.Context
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import cn.putong.commonlibrary.R


/**
 * Chrome Custom Tabs
 * Created by xinyi on 2018/1/19.
 */
object ChromeTab {

    fun getInstance(context: Context): CustomTabsIntent {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(
                ContextCompat.getColor(context, R.color.colorPrimary))
        return builder.build()
    }

}