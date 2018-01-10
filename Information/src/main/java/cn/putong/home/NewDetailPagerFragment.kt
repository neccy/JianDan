package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.adapter.NewDetailFragmentAdapter
import cn.putong.home.mvp.data.model.NewModel
import kotlinx.android.synthetic.main.fragment_newdetail_pager.*

/**
 * 新闻类型数据详情PAGER
 * Created by xinyi on 2018/1/10.
 */
@SuppressLint("ValidFragment")
class NewDetailPagerFragment(
        private val mNewDatas: List<NewModel.Post>,
        private val mCurrentPositon: Int) : BaseFragment() {

    private lateinit var mFragments: ArrayList<NewDetailFragment>
    private lateinit var mFragmentsAdapter: NewDetailFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_newdetail_pager)
    }

    override fun initData() {
        super.initData()
        initFrgamentList()
    }

    private fun initFrgamentList() {
        mFragments = arrayListOf()
        mNewDatas.forEach {
            mFragments.add(NewDetailFragment(it))
        }
        mFragmentsAdapter = NewDetailFragmentAdapter(childFragmentManager, mFragments)
    }

    override fun initView() {
        super.initView()
        initViewPager()
    }

    private fun initViewPager() {
        viewpager.adapter = mFragmentsAdapter
        viewpager.offscreenPageLimit = mFragments.size - 1
        viewpager.currentItem = mCurrentPositon
    }
}