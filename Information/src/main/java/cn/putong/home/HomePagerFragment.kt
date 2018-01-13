package cn.putong.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.adapter.HomeFragmentAdapter
import cn.putong.home.ui.HomePagerFragmentUi
import org.jetbrains.anko.AnkoContext

/**
 * 首页
 * Created by lala on 2018/1/14.
 */
class HomePagerFragment : BaseFragment() {

    private lateinit var mUi: HomePagerFragmentUi

    private lateinit var mFragmentAdapter: HomeFragmentAdapter
    private lateinit var mFragments: List<BaseFragment>

    private val mPagerCurrentItem = 1

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, this))
    }

    override fun initUi() {
        mUi = HomePagerFragmentUi()
    }

    override fun initData() {
        initFragmentList()
    }

    private fun initFragmentList() {
        mFragments = listOf(MeiZiFragment(), HomeFragment())
        mFragmentAdapter = HomeFragmentAdapter(childFragmentManager, mFragments)
    }

    override fun initView() {
        initViewPager()
    }

    private fun initViewPager() {
        mUi.viewpager.adapter = mFragmentAdapter
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.viewpager.currentItem = mPagerCurrentItem
    }
}