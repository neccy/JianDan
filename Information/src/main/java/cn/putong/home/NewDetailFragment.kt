package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.commonlibrary.util.setWebView
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.mvp.data.model.NewModel
import cn.putong.home.mvp.detail.model.NewDetailModel
import cn.putong.home.mvp.detail.present.DetailPresenter
import cn.putong.home.mvp.detail.view.IDetailView
import cn.putong.home.util.HtmlUtil
import kotlinx.android.synthetic.main.fragment_newdetail.*
import kotlinx.android.synthetic.main.view_newdetail_toolbar.*

@SuppressLint("ValidFragment")
class NewDetailFragment(private val mNewData: NewModel.Post) : BaseFragment(), IDetailView {

    private lateinit var mDetailPreSenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_newdetail)
    }

    override fun initData() {
        super.initData()
        mDetailPreSenter = DetailPresenter(this)
        initNewData()
    }

    private fun initNewData() {
        picview.setImageURI(mNewData.custom_fields.thumb_c[0])
        post_title.text = mNewData.title
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
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mDetailPreSenter.getNewThingsDetail()
    }

    override fun showLoading() {
        if (progressbar != null)
            progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        if (progressbar != null)
            progressbar.visibility = View.GONE
    }

    override fun successful(model: Any) {
        if (webview != null) {
            HtmlUtil.CONTENT = (model as NewDetailModel).post.content
            HtmlUtil.setUrl(webview)
        }
    }

    override fun error(msg: String) {
        TipBar.showTip(toolbar, msg)
    }

    override fun getDataId() = mNewData.id

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        initWebView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_new_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}