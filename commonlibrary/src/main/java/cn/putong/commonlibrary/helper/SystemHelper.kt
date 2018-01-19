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
fun Activity.copyText(view: View, url: String) {
    val clipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE)
                    as android.content.ClipboardManager
    clipboardManager.primaryClip =
            ClipData.newPlainText(null, url)
    TipBar.showTip(view, getString(R.string.copy_successful))
}
