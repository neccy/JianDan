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


/**
 * 新闻类型数据详情界面
 * Created by xinyi on 2018/1/10.
 */
@SuppressLint("ValidFragment")
class NewDetailFragment(private val mNewData: NewModel.Post)
    : BaseFragment(mSupportSwipBack = true), IDetailView {

    private lateinit var mDetailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_newdetail)
    }

    override fun initData() {
        super.initData()
        mDetailPresenter = DetailPresenter(this)
        initNewData()
    }

    private fun initNewData() {
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
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mDetailPresenter.getNewThingsDetail()
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }

    override fun successful(model: Any) {
        val html = (model as NewDetailModel).post.content
        webview.loadDataWithBaseURL(
                HtmlUtil.BASE_URL,
                HtmlUtil.getHtml(html),
                HtmlUtil.MIME_TYPE,
                HtmlUtil.ENCODING,
                HtmlUtil.HISTORY_URL)
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