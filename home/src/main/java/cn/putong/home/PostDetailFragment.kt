package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.otto.AppEvent
import cn.putong.commonlibrary.realm.information.InformationDB
import cn.putong.commonlibrary.helper.TimeHelper
import cn.putong.commonlibrary.helper.setWebView
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.event.PostRecordEvent
import cn.putong.home.mvp.data.model.PostModel
import cn.putong.home.mvp.detail.model.PostDetailModel
import cn.putong.home.mvp.detail.present.DetailPresenter
import cn.putong.home.mvp.detail.view.IDetailView
import cn.putong.home.helper.HtmlHelper
import kotlinx.android.synthetic.main.fragment_postdetail.*
import kotlinx.android.synthetic.main.view_postdetail_toolbar.*

@SuppressLint(value = ["ValidFragment"])
class PostDetailFragment(
        private val mNewData: PostModel.Post,
        private val mPosition: Int) : BaseFragment(), IDetailView {

    private lateinit var mDetailPreSenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_postdetail)
    }

    override fun initData() {
        mDetailPreSenter = DetailPresenter(this)
        initNewData()
    }

    private fun initNewData() {
        picview.setImageURI(mNewData.custom_fields.thumb_c[0])
        post_title.text = mNewData.title
        author.text = mNewData.author.nickname
        time.text = TimeHelper.format(TimeHelper.getDate(mNewData.date))
        excerpt.text = mNewData.excerpt
    }

    override fun initView() {
        initToolBar()
    }

    private fun initToolBar() {
        toolbar.setToolbar(mNewData.title, mIsBack = true)
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_grey600_24dp)
    }

    private fun initWebView() {
        if (webview != null)
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
            HtmlHelper.CONTENT = (model as PostDetailModel).post.content
            HtmlHelper.setUrl(webview)
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

    override fun onSupportVisible() {
        super.onSupportVisible()
        // 当前页面完全可见,添加当前新鲜事到已看记录,并更新新鲜事列表
        InformationDB.savePostRecord(mNewData.id, { result ->
            if (result) {
                // 通知更新列表
                mNewData.have_seen = true
                AppEvent.post(PostRecordEvent(mPosition))
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_post_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_comment ->
                start(PostCommentFragment(mNewData))
            R.id.action_more -> {

            }
        }
        return true
    }

}