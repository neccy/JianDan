package cn.putong.web

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.copyText
import cn.putong.commonlibrary.helper.openBrowser
import cn.putong.commonlibrary.helper.setWebView
import cn.putong.commonlibrary.helper.shareText
import cn.putong.commonlibrary.module.Module
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.fragment_web.*

/**
 * Web组件入口
 * Created by xinyi on 2018/1/19.
 */
@Route(path = Module.MODULE_WEB_PATH)
class WebFragment : BaseFragment() {

    private lateinit var mUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_web)
    }

    override fun initData() {
        initArgumentData()
    }

    private fun initArgumentData() {
        mUrl = arguments.getString(Module.PARAM_WEB_URL)
    }

    override fun initView() {
        initToolbar()
    }

    private fun initToolbar() {
        (toolbar as Toolbar).setToolbar(getString(R.string.app_name), mIsBack = true)
    }

    private fun initWebView() {
        webview.setWebView()
        webview.loadUrl(mUrl)
        webview.webViewClient = WebViewClientBase()
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        initWebView()
    }

    private inner class WebViewClientBase : WebViewClient() {
        override fun onPageStarted(
                view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            if (progressBar != null)
                progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if (toolbar != null)
                (toolbar as Toolbar).title = view?.title

            if (progressBar != null)
                progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_web, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_refresh ->
                webview.reload()
            R.id.action_share ->
                activity.shareText(webview.url)
            R.id.action_copylink ->
                activity.copyText(webview, webview.url)
            R.id.action_browser ->
                activity.openBrowser(webview.url)
        }
        return true
    }

    override fun onBackPressedSupport(): Boolean {
        if (webview.canGoBack()) {
            webview.goBack()
            return true
        } else {
            return false
        }
    }
}