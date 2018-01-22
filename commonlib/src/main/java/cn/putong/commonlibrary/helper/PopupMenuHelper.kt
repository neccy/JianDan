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
 * 显示更多选项menu
 */
fun Context.showMoreItemMenu(view: View,
                             shareListener: () -> Unit,
                             collectionListener: () -> Unit) {
    val mPopupMenu = PopupMenu(this, view)
    mPopupMenu.gravity = Gravity.END
    mPopupMenu.inflate(R.menu.comment_more_items)
    mPopupMenu.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.item_share ->
                shareListener.invoke()
            R.id.item_collection ->
                collectionListener.invoke()
        }
        true
    }
    mPopupMenu.show()
}
