package cn.putong.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.ModuleHelper
import cn.putong.commonlibrary.helper.TimeHelper
import cn.putong.commonlibrary.helper.setWebView
import cn.putong.commonlibrary.mvp.detail.model.PostDetailModel
import cn.putong.commonlibrary.mvp.detail.presenter.DetailPresenter
import cn.putong.commonlibrary.mvp.detail.view.IDetailView
import cn.putong.commonlibrary.mvp.home.model.PostModel
import cn.putong.commonlibrary.otto.AppEvent
import cn.putong.commonlibrary.otto.event.PostRecordEvent
import cn.putong.commonlibrary.realm.information.InformationDB
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.detail.helper.HtmlHelper
import cn.putong.detail.ui.PostDetailFragmentUi
import com.alibaba.android.arouter.launcher.ARouter
import org.jetbrains.anko.AnkoContext

@SuppressLint(value = ["ValidFragment"])
class PostDetailFragment(
        private val mNewData: PostModel.Post,
        private val mPosition: Int) : BaseFragment(), IDetailView {

    private lateinit var mUi: PostDetailFragmentUi
    private lateinit var mDetailPreSenter: DetailPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = PostDetailFragmentUi()
    }

    override fun initData() {
        mDetailPreSenter = DetailPresenter(IDetailView = this)
        initNewData()
    }

    private fun initNewData() {
        mUi.picview.setImageURI(mNewData.custom_fields.thumb_c[0])
        mUi.post_title.text = mNewData.title
        mUi.author.text = mNewData.author.nickname
        mUi.time.text = TimeHelper.format(TimeHelper.getDate(mNewData.date))
        mUi.excerpt.text = mNewData.excerpt
    }

    override fun initView() {
        initToolBar()
    }

    private fun initToolBar() {
        mUi.toolbar.setToolbar(mNewData.title, mIsBack = true)
        mUi.toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_grey600_24dp)
        mUi.toolbar.setNavigationOnClickListener {
            activity.finish()
        }
    }

    private fun initWebView() {
        mUi.webview.setWebView()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mDetailPreSenter.getNewThingsDetail()
    }

    override fun initListener() {
        mUi.picview.setOnClickListener {
            ARouter.getInstance()
                    .build(ModuleHelper.GALLERY_MODULE_PATH)
                    .withStringArrayList(ModuleHelper.PARAM_COMMENT_MODEL_PICS, mNewData.custom_fields.thumb_c)
                    .navigation()
        }
    }

    override fun showLoading() {
        mUi.progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mUi.progressbar.visibility = View.GONE
    }

    override fun successful(model: Any) {
        HtmlHelper.CONTENT = (model as PostDetailModel).post.content
        HtmlHelper.setUrl(mUi.webview)
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
            if (result)
                AppEvent.post(PostRecordEvent(mPosition))
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