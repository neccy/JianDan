package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.setColor
import cn.putong.commonlibrary.helper.setDefaultDivider
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.adapter.PostCommentAdapter
import cn.putong.home.mvp.data.model.PostModel
import cn.putong.home.mvp.detail.model.PostCommentModel
import cn.putong.home.mvp.detail.present.DetailPresenter
import cn.putong.home.mvp.detail.view.IDetailView
import cn.putong.home.ui.PostCommentFragmentUi
import org.jetbrains.anko.AnkoContext

@SuppressLint(value = ["ValidFragment"])
class PostCommentFragment(private val mNewData: PostModel.Post) :
        BaseFragment(), IDetailView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mUi: PostCommentFragmentUi

    private lateinit var mCommentPreSenter: DetailPresenter
    private lateinit var mCommentsAdapter: PostCommentAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            mUi.createView(AnkoContext.Companion.create(context, owner = this))

    override fun initUi() {
        mUi = PostCommentFragmentUi()
    }

    override fun initData() {
        mCommentPreSenter = DetailPresenter(IDetailView = this)
        initAdapter()
    }

    private fun initAdapter() {
        mCommentsAdapter = PostCommentAdapter()
    }

    override fun initView() {
        initToolBar()
        initRefreshLayout()
        initListView()
    }

    private fun initToolBar() {
        mUi.toolbar.setToolbar(getString(R.string.postcomment_toolbar_title), mIsBack = true)
    }

    private fun initRefreshLayout() {
        mUi.refresh.setColor()
        mUi.refresh.setOnRefreshListener(this)
    }

    private fun initListView() {
        mUi.listview.setDefaultDivider(context)
        mUi.listview.adapter = mCommentsAdapter
    }

    override fun showLoading() {
        mUi.refresh.isRefreshing = true
    }

    override fun hideLoading() {
        mUi.refresh.isRefreshing = false
    }

    override fun successful(model: Any) {
        val mPostCommentModel = model as PostCommentModel
        mPostCommentModel.updateListView()
    }

    override fun getDataId() = mNewData.id

    override fun error(msg: String) {
        TipBar.showTip(mUi.toolbar, msg)
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        mCommentPreSenter.getNewThingsComments()
    }

    override fun onRefresh() {
        mCommentPreSenter.getNewThingsComments()
    }

    private fun PostCommentModel.updateListView() {
        mCommentsAdapter.updateList(post.comments.sortedByDescending { it.date })
    }
}