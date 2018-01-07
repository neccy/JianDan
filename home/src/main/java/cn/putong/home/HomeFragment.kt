package cn.putong.home

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.adapter.DataListFragmentAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 首页
 * Created by xinyi on 2018/1/6.
 */
class HomeFragment : BaseFragment() {

    private lateinit var mFragmentsAdapter: DataListFragmentAdapter
    private lateinit var mFragments: ArrayList<DataListFragment>
    private lateinit var mClassItems: Array<String>

    companion object {
        // 新鲜事
        val CLASS_NEWTHINGS = 1
        // 无聊图
        val CLASS_BORINGPICTURES = 2
        // 段子
        val CLASS_DUANZI = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
    }

    override fun initData() {
        super.initData()
        initClassItems()
        initFragmentList()
    }

    private fun initClassItems() {
        mClassItems = resources.getStringArray(R.array.home_class_item)
    }

    private fun initFragmentList() {
        mFragments = ArrayList()
        mFragments.add(DataListFragment(CLASS_NEWTHINGS))
        mFragments.add(DataListFragment(CLASS_BORINGPICTURES))
        mFragments.add(DataListFragment(CLASS_DUANZI))
        mFragmentsAdapter = DataListFragmentAdapter(
                childFragmentManager, mFragments, mClassItems)
    }

    override fun initView() {
        super.initView()
        initToolBar()
        initTabLayout()
        initViewPager()
    }

    private fun initToolBar() {
        (toolbar as Toolbar).setToolbar(getString(R.string.app_name))
    }

    private fun initTabLayout() {
        mClassItems.forEach { tablayout.addTab(tablayout.newTab().setText(it)) }
    }

    private fun initViewPager() {
        viewpager.adapter = mFragmentsAdapter
        viewpager.offscreenPageLimit = mFragments.size - 1
        tablayout.setupWithViewPager(viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}