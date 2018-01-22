package cn.putong.commonlibrary.widget

import android.support.design.widget.Snackbar
import android.view.View

/**
 * SnackBar封装
 * Created by xinyi on 2018/1/8.
 */
object TipBar {

    /**
     * 显示基本SnackBar
     */
    fun showTip(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

}