package cn.putong.commonlibrary.helper

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.view.View
import cn.putong.commonlibrary.R
import cn.putong.commonlibrary.widget.TipBar

/**
 * 复制文本内容
 */
fun Activity.copyText(view: View, msg: String) {
    val clipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE)
                    as android.content.ClipboardManager
    clipboardManager.primaryClip =
            ClipData.newPlainText(null, msg)
    TipBar.showTip(view, getString(R.string.copy_successful))
}
