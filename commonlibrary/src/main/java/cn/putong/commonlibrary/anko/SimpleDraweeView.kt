package cn.putong.commonlibrary.anko

import android.view.ViewManager
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.custom.ankoView

/**
 * 自定义Anko Fresco
 * Created by xinyi on 2018/1/13.
 */

inline fun ViewManager.simpleDraweeView(theme: Int = 0): SimpleDraweeView = simpleDraweeView(theme) {}

inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: SimpleDraweeView.() -> Unit) =
        ankoView(::SimpleDraweeView, theme) { init() }