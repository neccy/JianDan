package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.base.BaseRecyclerAdapter
import cn.putong.commonlibrary.util.setColor
import cn.putong.commonlibrary.util.setDefaultDivider
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.adapter.BoringPicAdapter
import cn.putong.home.adapter.NewThingsAdapter
import cn.putong.home.mvp.data.model.BoringPicturesModel
import cn.putong.home.mvp.data.model.NewThingsModel
import cn.putong.home.mvp.data.prensent.DataPresenter
import cn.putong.home.mvp.data.view.IDataView
import kotlinx.android.synthetic.main.fragment_datalist.*

/**
 * 首页数据列表界面
 * Created by lala on 2018/1/7.
 */
@SuppressLint("ValidFragment")
class DataListFragment(private val mClass: Int) : BaseFragment(), IDataView {

    private var mCurrentPage = 1
    private var mLongingMore = false

    // 新鲜事数据
    private lateinit var mNewThingsData: ArrayList<NewThingsModel.Post>
    private lateinit var mNewThingsAdapter: NewThingsAdapter

    // 无聊图数据
    private lateinit var mBoringPictureData: ArrayList<BoringPicturesModel.Comment>
    private lateinit var mBoringPicturesAdapter: BoringPicAdapter

    private lateinit var mDataPrensent: DataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_datalist)
    }

    override fun initData() {
        super.initData()
        mDataPrensent = DataPresenter(this)
        initAdapter()
    }

    private fun initAdapter() {
        mNewThingsAdapter = NewThingsAdapter()
        mBoringPicturesAdapter = BoringPicAdapter()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initRefreshLayout()
        initListView()
        getData()
    }

    private fun initRefreshLayout() {
        refresh.setColor()
        refresh.setOnRefreshListener { getData() }
    }

    private fun initListView() {
        listview.adapter = getAdapter()
        if (mClass == HomeFragment.CLASS_NEWTHINGS)
            listview.setDefaultDivider(context)
    }

    override fun initListener() {
        super.initListener()
        listview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        if (!refresh.isRefreshing && !mLongingMore)
            progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        // 正在首次刷新
        if (progressbar.visibility == View.VISIBLE)
            progressbar.visibility = View.GONE

        // 正在刷新
        if (refresh.isRefreshing)
            refresh.isRefreshing = false

        // 正在加载
        if (mLongingMore) {
            mLongingMore = false
            getAdapter().removeFooter()
        }
    }

    override fun successful(model: Any) {
        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS -> {
                val mNewThingsModel = model as NewThingsModel
                mNewThingsData.addAll(mNewThingsModel.posts)
                mNewThingsAdapter.updateList(mNewThingsData)
            }
            HomeFragment.CLASS_BORINGPICTURES -> {
                val mBoringPicturesModel = model as BoringPicturesModel
                mBoringPictureData.addAll(mBoringPicturesModel.comments)
                mBoringPicturesAdapter.updateList(mBoringPictureData)
            }
            HomeFragment.CLASS_DUANZI -> {
            }
        }
    }

    override fun error(msg: String) {
        TipBar.showTip(data_content, msg)
    }

    override fun getCurrentPage() = mCurrentPage

    /**
     * 根据类型获取对应适配器
     */
    private fun getAdapter(): BaseRecyclerAdapter {
        return when (mClass) {
            HomeFragment.CLASS_NEWTHINGS ->
                mNewThingsAdapter
            else ->
                mBoringPicturesAdapter
        }
    }

    /**
     * 根据类型获取数据
     * @param mLoading 是否加载更多
     */
    private fun getData(mLoading: Boolean = false) {
        if (!mLoading) {
            mCurrentPage = 1
            mNewThingsData = ArrayList()
            mBoringPictureData = ArrayList()
        } else {
            mCurrentPage += 1
        }

        if (mLoading)
            getAdapter().addFooter()

        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS ->
                mDataPrensent.getNewThings()
            HomeFragment.CLASS_BORINGPICTURES ->
                mDataPrensent.getBoringPictures()
        }
    }

}
