package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.otto.AppEvent
import cn.putong.commonlibrary.realm.information.InformationDB
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.commonlibrary.util.setWebView
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.event.PostRecordEvent
import cn.putong.home.mvp.data.model.PostModel
import cn.putong.home.mvp.detail.model.PostDetailModel
import cn.putong.home.mvp.detail.present.DetailPresenter
import cn.putong.home.mvp.detail.view.IDetailView
import cn.putong.home.ui.PostDetailFragmentUi
import cn.putong.home.util.HtmlUtil
import org.jetbrains.anko.AnkoContext

@SuppressLint("ValidFragment")
class PostDetailFragment(private val mNewData: PostModel.Post, private val mPosition: Int)
    : BaseFragment(), IDetailView {

    private lateinit var mUi: PostDetailFragmentUi
    private lateinit var mDetailPreSenter: DetailPresenter

//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
//            mUi.createView(AnkoContext.Companion.create(context, owner = this))

    override fun initUi() {
        mUi = PostDetailFragmentUi()
    }

    override fun initData() {
        mDetailPreSenter = DetailPresenter(this)
        initNewData()
    }

    private fun initNewData() {
        mUi.picview.setImageURI(mNewData.custom_fields.thumb_c[0])
        mUi.post_title.text = mNewData.title
        mUi.author.text = mNewData.author.nickname
        mUi.time.text = TimeUtil.format(TimeUtil.getDate(mNewData.date))
        mUi.excerpt.text = mNewData.excerpt
    }

    override fun initView() {
        initToolBar()
    }

    private fun initToolBar() {
        mUi.toolbar.setToolbar(mNewData.title, mIsBack = true)
        mUi.toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_grey600_24dp)
    }

    private fun initWebView() {
        if (mUi.webview != null)
            mUi.webview!!.setWebView()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mDetailPreSenter.getNewThingsDetail()
    }

    override fun showLoading() {
        if (mUi.progressbar != null)
            mUi.progressbar!!.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        if (mUi.progressbar != null)
            mUi.progressbar!!.visibility = View.GONE
    }

    override fun successful(model: Any) {
        if (mUi.webview != null) {
            HtmlUtil.CONTENT = (model as PostDetailModel).post.content
            HtmlUtil.setUrl(mUi.webview!!)
        }
    }

    override fun error(msg: String) {
        TipBar.showTip(mUi.toolbar, msg)
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
        }
        return true
    }

}