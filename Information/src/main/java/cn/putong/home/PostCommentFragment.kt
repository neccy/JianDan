package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.util.setColor
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.mvp.data.model.PostModel
import cn.putong.home.mvp.detail.present.DetailPresenter
import cn.putong.home.mvp.detail.view.IDetailView
import kotlinx.android.synthetic.main.fragment_postcomment.*

/**
 * 新闻类型数据评论界面
 * Created by xinyi on 2018/1/12.
 */
@SuppressLint("ValidFragment")
class PostCommentFragment(private val mNewData: PostModel.Post) :
        BaseFragment(), IDetailView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mCommentPreSenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_postcomment)
    }

    override fun initData() {
        super.initData()
        mCommentPreSenter = DetailPresenter(this)
    }

    override fun initView() {
        super.initView()
        initToolBar()
        initRefreshLayout()
    }

    private fun initToolBar() {
        (toolbar as Toolbar).setToolbar(getString(R.string.postcomment_toolbar_title), mIsBack = true)
    }

    private fun initRefreshLayout() {
        refresh.setColor()
        refresh.setOnRefreshListener(this)
    }

    override fun loadData() {
        super.loadData()
        mCommentPreSenter.getNewThingsComments()
    }

    override fun showLoading() {
        refresh.isRefreshing = true
    }

    override fun hideLoading() {
        refresh.isRefreshing = false
    }

    override fun successful(model: Any) {

    }

    override fun getDataId() = mNewData.id

    override fun error(msg: String) {
        TipBar.showTip(toolbar, msg)
    }

    override fun onRefresh() {
        mCommentPreSenter.getNewThingsComments()
    }

}