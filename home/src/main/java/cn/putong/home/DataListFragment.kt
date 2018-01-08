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
import kotlinx.android.synthetic.main.fragment_datalist.*

@SuppressLint("ValidFragment")
/**
 * 首页数据列表界面
 * Created by lala on 2018/1/7.
 */
class DataListFragment(private val mClass: Int) : BaseFragment(), IDataView {

    // 当前页数
    private var mCurrentPage = 1

    private lateinit var mNewThingsModel: NewThingsModel
    private lateinit var mNewTingsAdapter: NewThingsAdapter
    private lateinit var mDataPrensent: DataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_datalist)
    }

    override fun initData() {
        super.initData()
        mDataPrensent = DataPresenter(this)
        initModel()
        initAdapter()
    }

    private fun initModel() {
        mNewThingsModel = NewThingsModel()
    }

    private fun initAdapter() {
        mNewTingsAdapter = NewThingsAdapter()
    }

    override fun initView() {
        super.initView()
        initListView()
    }

    private fun initListView() {
        listview.setDefaultDivider(context)
        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS ->
                listview.adapter = mNewTingsAdapter
        }
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        getDatas()
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }

    override fun successful(model: Any) {
        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS -> {
                mNewThingsModel = model as NewThingsModel
                mNewTingsAdapter.updateList(mNewThingsModel.posts)
            }

            HomeFragment.CLASS_BORINGPICTURES -> {
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
     * 根据类型获取数据
     */
    private fun getDatas() {
        when (mClass) {
            HomeFragment.CLASS_NEWTHINGS ->
                // 新鲜事
                mDataPrensent.getNewThings()
        }
    }
}