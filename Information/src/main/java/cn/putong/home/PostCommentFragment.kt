package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.util.setColor
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.mvp.data.model.PostModel
import cn.putong.home.mvp.detail.present.DetailPresenter
import cn.putong.home.mvp.detail.view.IDetailView
import cn.putong.home.ui.PostCommentFragmentUi
import org.jetbrains.anko.AnkoContext

/**
 * 新闻类型数据评论界面
 * Created by xinyi on 2018/1/12.
 */
@SuppressLint("ValidFragment")
class PostCommentFragment(private val mNewData: PostModel.Post) :
        BaseFragment(), IDetailView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mUi: PostCommentFragmentUi
    private lateinit var mCommentPreSenter: DetailPresenter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            mUi.createView(AnkoContext.Companion.create(context, owner = this))

    override fun initUi() {
        mUi = PostCommentFragmentUi()
    }

    override fun initData() {
        mCommentPreSenter = DetailPresenter(this)
    }

    override fun initView() {
        initRefreshLayout()
        initToolBar()
    }

    private fun initRefreshLayout() {
        mUi.refresh.setColor()
        mUi.refresh.setOnRefreshListener(this)
    }

    private fun initToolBar() {
        mUi.toolbar.setToolbar(getString(R.string.postcomment_toolbar_title), mIsBack = true)
    }

    override fun loadData() {
        mCommentPreSenter.getNewThingsComments()
    }

    override fun showLoading() {
        mUi.refresh.isRefreshing = true
    }

    override fun hideLoading() {
        mUi.refresh.isRefreshing = false
    }

    override fun successful(model: Any) {

    }

    override fun getDataId() = mNewData.id

    override fun error(msg: String) {
        TipBar.showTip(mUi.toolbar, msg)
    }

    override fun onRefresh() {
        mCommentPreSenter.getNewThingsComments()
    }

}