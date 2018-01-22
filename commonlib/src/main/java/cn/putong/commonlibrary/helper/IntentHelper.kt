package cn.putong.commonlibrary.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import cn.putong.commonlibrary.R

/**
 * 分享文本内容
 */
fun Activity.shareText(url: String) {
    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.putExtra(Intent.EXTRA_TEXT, url)
    mIntent.type = "text/plain"
    startActivity(Intent.createChooser(
            mIntent, getString(R.string.app_name)))
}

/**
 * 浏览器打开连接
 */
fun Activity.openBrowser(url: String) {
    val mIntent = Intent(Intent.ACTION_VIEW)
    mIntent.setDataAndType(Uri.parse(url), "text/html");
    startActivity(mIntent)
}
