package cn.putong.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.adapter.DataListFragmentAdapter
import cn.putong.home.ui.HomeFragmentUi
import cn.putong.home.util.DataClass
import org.jetbrains.anko.AnkoContext

/**
 * 首页
 * Created by xinyi on 2018/1/6.
 */
class HomeFragment : BaseFragment() {

    private lateinit var mUi: HomeFragmentUi

    private lateinit var mFragmentsAdapter: DataListFragmentAdapter
    private lateinit var mFragments: ArrayList<DataListFragment>
    private lateinit var mClassItems: Array<String>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            mUi.createView(AnkoContext.Companion.create(context, owner = this))

    override fun initUi() {
        mUi = HomeFragmentUi()
    }

    override fun initData() {
        initClassItems()
        initFragmentList()
    }

    private fun initClassItems() {
        mClassItems = resources.getStringArray(R.array.home_class_item)
    }

    private fun initFragmentList() {
        mFragments = ArrayList()
        mFragments.add(DataListFragment(DataClass.CLASS_NEWTHINGS))
        mFragments.add(DataListFragment(DataClass.CLASS_BORINGPICTURES))
        mFragments.add(DataListFragment(DataClass.CLASS_DUANZI))
        mFragmentsAdapter =
                DataListFragmentAdapter(childFragmentManager, mFragments, mClassItems)
    }

    override fun initView() {
        initToolBar()
        initTabLayout()
        initViewPager()
    }

    private fun initToolBar() {
        mUi.toolbar.setToolbar(getString(R.string.app_name))
    }

    private fun initTabLayout() {
        mClassItems.forEach { mUi.tablayout.addTab(mUi.tablayout.newTab().setText(it)) }
    }

    private fun initViewPager() {
        mUi.viewpager.adapter = mFragmentsAdapter
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.tablayout.setupWithViewPager(mUi.viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
    }

}