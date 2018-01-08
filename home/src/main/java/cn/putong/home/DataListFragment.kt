package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.util.setDefaultDivider
import cn.putong.commonlibrary.widget.TipBar
import cn.putong.home.adapter.NewThingsAdapter
import cn.putong.home.mvp.data.model.NewThingsModel
import cn.putong.home.mvp.data.prensent.DataPresenter
import cn.putong.home.mvp.data.view.IDataView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_datalist.*

@SuppressLint("ValidFragment")

/**
 * 首页数据列表界面
 * Created by lala on 2018/1/7.
 */
class DataListFragment(private val mClass: Int) : BaseFragment(), IDataView,
        OnRefreshListener,
        OnLoadmoreListener {

    // 当前页数
    private var mCurrentPage = 1

    //新鲜事数据
    private lateinit var mNewThingsData: ArrayList<NewThingsModel.Post>
    private lateinit var mNewThingsAdapter: NewThingsAdapter

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
    }

    override fun initView() {
        super.initView()
        initRefreshLayout()
        initListView()
    }

    private fun initRefreshLayout() {
        refresah.setOnRefreshListener(this)
        refresah.setOnLoadmoreListener(this)
    }

    private fun initListView() {
        listview.setDefaultDivider(context)
        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS ->
                listview.adapter = mNewThingsAdapter
        }
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        getData()
    }

    override fun showLoading() {
        if (!refresah.isRefreshing && !refresah.isLoading)
            progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE

        // 正在刷新
        if (refresah.isRefreshing)
            refresah.finishRefresh()

        // 正在加载
        if (refresah.isLoading)
            refresah.finishLoadmore()
    }

    override fun successful(model: Any) {
        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS -> {
                // 新鲜事
                val mNewThingsModel = model as NewThingsModel
                mNewThingsData.addAll(mNewThingsModel.posts)
                mNewThingsAdapter.updateList(mNewThingsData)
            }

            HomeFragment.CLASS_BORINGPICTURES -> {
                // 无聊图
            }

            HomeFragment.CLASS_DUANZI -> {
                // 段子
            }
        }
    }

    override fun error(msg: String) {
        TipBar.showTip(data_content, msg)
    }

    override fun getCurrentPage() = mCurrentPage

    /**
     * 刷新数据
     */
    override fun onRefresh(refreshlayout: RefreshLayout?) {
        getData()
    }

    /**
     * 加载更多数据
     */
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        getData(true)
    }

    /**
     * 根据类型获取数据
     * @param isLoadMore 是否加载更多
     */
    private fun getData(isLoadMore: Boolean = false) {
        if (!isLoadMore) {
            //下拉加载,初始化页数和集合,防止数据重复
            mCurrentPage = 1
            mNewThingsData = ArrayList()
        } else {
            //上拉加载更多,页数加1
            mCurrentPage += 1
        }

        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS ->
                // 新鲜事
                mDataPrensent.getNewThings()
        }
    }

}