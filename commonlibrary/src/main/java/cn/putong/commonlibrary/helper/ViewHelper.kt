package cn.putong.commonlibrary.helper

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.putong.commonlibrary.R

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
fun SwipeRefreshLayout.setColor(resid: Int = R.color.colorAccent) {
    setColorSchemeResources(resid)
}

/**
 * WebView相关设置
 */
fun WebView.setWebView() {
    val mWebSett = settings
    mWebSett.allowUniversalAccessFromFileURLs = true
    // 支持Javascript
    mWebSett.javaScriptEnabled = true
    // 允许访问文件
    mWebSett.allowFileAccess = true
    // 设置显示缩放按钮
    mWebSett.builtInZoomControls = false
    // 支持缩放
    mWebSett.setSupportZoom(false)
    mWebSett.setAppCacheEnabled(true)
    // 设置缓存模式
    mWebSett.cacheMode = WebSettings.LOAD_DEFAULT;
    // 禁止加载Url时弹出浏览器
    webViewClient = WebViewClient()
}