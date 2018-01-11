package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.commonlibrary.util.setWebView
import cn.putong.home.mvp.data.model.NewModel
import kotlinx.android.synthetic.main.fragment_newdetail.*
import kotlinx.android.synthetic.main.view_newdetail_toolbar.*

/**
 * 新闻类型数据详情界面
 * Created by xinyi on 2018/1/10.
 */
@SuppressLint("ValidFragment")
class NewDetailFragment(private val mNewData: NewModel.Post) :
        BaseFragment(mSupportSwipBack = true) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_newdetail)
    }

    override fun initData() {
        super.initData()
        picview.setImageURI(mNewData.custom_fields.thumb_c[0])
        title.text = mNewData.title
        author.text = mNewData.author.nickname
        time.text = TimeUtil.format(TimeUtil.getDate(mNewData.date))
        excerpt.text = mNewData.excerpt
    }

    override fun initView() {
        super.initView()
        initToolBar()
    }

    private fun initToolBar() {
        toolbar.setToolbar(mNewData.title, mIsBack = true)
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_grey600_24dp)
    }

    private fun initWebView() {
        webview.setWebView()
        webview.webViewClient = WebViewClient()
    }

    private inner class WebViewClientBase : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        initWebView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_new_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}