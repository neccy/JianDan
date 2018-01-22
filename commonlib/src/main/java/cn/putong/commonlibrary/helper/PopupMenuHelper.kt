package cn.putong.commonlibrary.helper

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.view.Gravity
import android.view.View
import cn.putong.commonlibrary.R

/**
 * PopupMenu工具类
 * Created by xinyi on 2018/1/22.
 */


/**
 * 显示Comment数据更多Menu
 */
fun Context.showCommentMoreMenu(view: View, menu: Int) {
    val mPopupMenu = PopupMenu(this, view)
    mPopupMenu.gravity = Gravity.END
    mPopupMenu.inflate(menu)
    mPopupMenu.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.item_share -> {

            }
            R.id.item_collection -> {

            }
            R.id.item_copytext -> {

            }
        }
        true
    }
    mPopupMenu.show()
}
