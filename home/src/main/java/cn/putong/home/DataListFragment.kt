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
import cn.putong.commonlibrary.helper.getUnWelcomeValue
import cn.putong.commonlibrary.helper.setColor
import cn.putong.commonlibrary.helper.setDefaultDivider
import cn.putong.commonlibrary.module.ModuleHelper
import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel
import cn.putong.commonlibrary.mvp.home.present.DataPresenter
import cn.putong.commonlibrary.mvp.home.view.IDataView
import cn.putong.commonlibrary.otto.event.PostRecordEvent
import cn.putong.commonlibrary.otto.event.UnWelComeEvent
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.adapter.CommentDataAdapter
import cn.putong.home.adapter.PostDataAdapter
import cn.putong.home.ui.DataListFragmentUi
import com.squareup.otto.Subscribe
import org.jetbrains.anko.AnkoContext

@SuppressLint(value = ["ValidFragment"])
class DataListFragment(private val mTemPlate: Int) : BaseFragment(), IDataView {

    private var mCurrentPage = 1
    private var mLongingMore = false

    private lateinit var mUi: DataListFragmentUi
    private lateinit var mDataPrenSent: DataPresenter

    private lateinit var mPostDatas: ArrayList<PostModel.Post>
    private lateinit var mPostAdapter: PostDataAdapter

    private lateinit var mCommentDatas: ArrayList<CommentModel.Comment>
    private lateinit var mCommentCaches: ArrayList<CommentModel.Comment>
    private lateinit var mCommentAdapter: CommentDataAdapter

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
//            ModuleHelper.startPosDetailtModule(mParentFragment,
//                    position, mPostDatas[position])
            ModuleHelper.startWebModule(mParentFragment,"http://www.baidu.com")
        }
        mCommentAdapter = CommentDataAdapter(ArrayList(), { pics ->
            ModuleHelper.startGalleryModule(mParentFragment, pics)
        })
    }

    override fun initView() {
        initRefreshLayout()
        initListView()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        getData()
    }

    private fun initRefreshLayout() {
        mUi.refresh.setColor()
        mUi.refresh.setOnRefreshListener { getData() }
    }

    private fun initListView() {
        mUi.listview.adapter = getAdapter()
        if (mTemPlate == TemPlateHelper.NEWTHINGS)
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
        if (mUi.progressbar.visibility == View.VISIBLE)
            mUi.progressbar.visibility = View.GONE

        if (mUi.refresh.isRefreshing)
            mUi.refresh.isRefreshing = false

        if (mLongingMore) {
            mLongingMore = false
            getAdapter().removeFooter()
        }
    }

    override fun successful(model: Any) {
        if (mTemPlate == TemPlateHelper.NEWTHINGS) {
            val mNewThingsModel = model as PostModel
            mPostDatas.addAll(mNewThingsModel.posts)
            updatePostList()
        } else {
            val mBoringPicturesModel = model as CommentModel
            mCommentCaches.addAll(mBoringPicturesModel.comments)
            updateCommentList()
        }
    }

    override fun error(msg: String) {
        TipBar.showTip(mUi.listview, msg)
    }

    override fun getCurrentPage(): Int {
        return mCurrentPage
    }

    private fun getAdapter(): BaseRecyclerAdapter {
        return if (mTemPlate == TemPlateHelper.NEWTHINGS)
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
            mCommentCaches = ArrayList()
        } else {
            mCurrentPage += 1
        }

        if (mLoading)
            getAdapter().addFooter()

        when (mTemPlate) {
            TemPlateHelper.NEWTHINGS ->
                mDataPrenSent.getNewThings()
            TemPlateHelper.BORINGPICS ->
                mDataPrenSent.getBoringPics()
            TemPlateHelper.MEIZIPICS ->
                mDataPrenSent.getMeiZiPics()
            TemPlateHelper.DUANZIS ->
                mDataPrenSent.getDuanZis()
        }
    }

    private fun updatePostList() {
        mPostAdapter.updateList(mPostDatas)
    }

    private fun updateCommentList() {
        mCommentDatas =
                if (getUnWelcomeValue(context))
                    mCommentCaches.filter {
                        it.vote_negative.toInt() < 100
                    } as ArrayList<CommentModel.Comment>
                else
                    mCommentCaches
        mCommentAdapter.updateList(mCommentDatas)
    }

    /**
     * 根据详情页面发送过来的下标更新已看状态
     */
    @Subscribe
    fun getPositionUpdateAdapter(record: PostRecordEvent) {
        if (mTemPlate == TemPlateHelper.NEWTHINGS)
            mPostAdapter.notifyItemChanged(record.position)
    }

    /**
     * 根据发送过来的通知更新Comment适配器
     */
    @Subscribe
    fun getUnWelComeValue(unWelCome: UnWelComeEvent) {
        updateCommentList()
    }

}
