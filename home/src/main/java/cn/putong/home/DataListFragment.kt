package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.base.BaseRecyclerAdapter
import cn.putong.commonlibrary.helper.TemPlateHelper
import cn.putong.commonlibrary.helper.setColor
import cn.putong.commonlibrary.helper.setDefaultDivider
import cn.putong.commonlibrary.module.ModuleHelper
import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel
import cn.putong.commonlibrary.mvp.home.present.DataPresenter
import cn.putong.commonlibrary.mvp.home.view.IDataView
import cn.putong.commonlibrary.otto.event.PostRecordEvent
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.adapter.CommentDataAdapter
import cn.putong.home.adapter.PostDataAdapter
import cn.putong.home.ui.DataListFragmentUi
import com.squareup.otto.Subscribe
import org.jetbrains.anko.AnkoContext

@SuppressLint(value = ["ValidFragment"])
class DataListFragment(private val mClass: Int) : BaseFragment(), IDataView {

    private lateinit var mUi: DataListFragmentUi

    private var mCurrentPage = 1
    private var mLongingMore = false

    // 新闻类型数据(新鲜事)
    private lateinit var mPostDatas: ArrayList<PostModel.Post>
    private lateinit var mPostAdapter: PostDataAdapter

    // 评论类型数据(无聊图,段子)
    private lateinit var mCommentDatas: ArrayList<CommentModel.Comment>
    private lateinit var mCommentAdapter: CommentDataAdapter

    private lateinit var mDataPrenSent: DataPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = DataListFragmentUi()
    }

    override fun initData() {
        mDataPrenSent = DataPresenter(IDataView = this)
        initAdapter()
    }

    private fun initAdapter() {
        val mParentFragment = (parentFragment as HomeFragment)
        mPostAdapter = PostDataAdapter { position ->
            ModuleHelper
                    .startPosDetailtModule(mParentFragment, position, mPostDatas[position])
        }
        mCommentAdapter = CommentDataAdapter(arrayListOf(), { pics ->
            ModuleHelper
                    .startGalleryModule(mParentFragment, pics)
        })
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initRefreshLayout()
        initListView()
        getData()
    }

    private fun initRefreshLayout() {
        mUi.refresh.setColor()
        mUi.refresh.setOnRefreshListener { getData() }
    }

    private fun initListView() {
        mUi.listview.adapter = getAdapter()
        if (mClass == TemPlateHelper.NEWTHINGS)
            mUi.listview.setDefaultDivider(context)
    }

    override fun initListener() {
        mUi.listview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                if (!mLongingMore && linearLayoutManager.itemCount ==
                        (linearLayoutManager.findLastVisibleItemPosition() + 1)) {
                    mLongingMore = true
                    getData(mLongingMore)
                }
            }
        })
    }

    override fun showLoading() {
        if (!mUi.refresh.isRefreshing && !mLongingMore)
            mUi.progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        // 正在首次刷新
        if (mUi.progressbar.visibility == View.VISIBLE)
            mUi.progressbar.visibility = View.GONE

        // 正在刷新
        if (mUi.refresh.isRefreshing)
            mUi.refresh.isRefreshing = false

        // 正在加载
        if (mLongingMore) {
            mLongingMore = false
            getAdapter().removeFooter()
        }
    }

    override fun successful(model: Any) {
        if (mClass == TemPlateHelper.NEWTHINGS) {
            val mNewThingsModel = model as PostModel
            mPostDatas.addAll(mNewThingsModel.posts)
            mPostAdapter.updateList(mPostDatas)
        } else {
            val mBoringPicturesModel = model as CommentModel
            mCommentDatas.addAll(mBoringPicturesModel.comments)
            mCommentAdapter.updateList(mCommentDatas)
        }
    }

    override fun error(msg: String) {
        TipBar.showTip(mUi.listview, msg)
    }

    override fun getCurrentPage() = mCurrentPage

    /**
     * 根据类型获取对应适配器
     */
    private fun getAdapter(): BaseRecyclerAdapter {
        return if (mClass == TemPlateHelper.NEWTHINGS)
            mPostAdapter
        else
            mCommentAdapter
    }

    /**
     * 根据类型获取数据
     * @param mLoading 是否加载更多
     */
    private fun getData(mLoading: Boolean = false) {
        if (!mLoading) {
            mCurrentPage = 1
            mPostDatas = ArrayList()
            mCommentDatas = ArrayList()
        } else {
            mCurrentPage += 1
        }

        if (mLoading)
            getAdapter().addFooter()

        when (mClass) {
            TemPlateHelper.NEWTHINGS ->
                mDataPrenSent.getNewThings()
            TemPlateHelper.BORINGPICS ->
                mDataPrenSent.getBoringPics()
            TemPlateHelper.MEIZIPICS ->
                mDataPrenSent.getMeiZiPics()
            TemPlateHelper.DUANZI ->
                mDataPrenSent.getDuanZis()
        }
    }

    /**
     * 根据详情页面发送过来的下标更新适配器
     */
    @Subscribe
    fun getPositionUpdateAdapter(recordEvent: PostRecordEvent) {
        if (mClass == TemPlateHelper.NEWTHINGS) {
            mPostAdapter.notifyItemChanged(recordEvent.position)
        }
    }

}
