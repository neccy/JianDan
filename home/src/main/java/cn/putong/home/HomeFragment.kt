package cn.putong.home

import android.os.Bundle
import android.view.*
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.DataClassHelper
import cn.putong.commonlibrary.module.Module
import cn.putong.home.adapter.DataListFragmentAdapter
import cn.putong.home.helper.HawkHelper
import cn.putong.home.ui.HomeFragmentUi
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jetbrains.anko.AnkoContext

/**
 * 首页组件入口
 * Created by xinyi on 2018/1/6.
 */
@Route(path = Module.MODULE_HOME_PATH)
class HomeFragment : BaseFragment() {

    private lateinit var mUi: HomeFragmentUi

    private lateinit var mFragmentsAdapter: DataListFragmentAdapter
    private lateinit var mFragments: ArrayList<DataListFragment>
    private lateinit var mClassItems: Array<String>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            mUi.createView(AnkoContext.Companion.create(context, owner = this))

    override fun onDestroy() {
        super.onDestroy()
        // 记录Tab下标
        HawkHelper.saveTabSelection(mUi.viewpager.currentItem)
    }

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
        mFragments.add(DataListFragment(DataClassHelper.CLASS_NEWTHINGS))
        mFragments.add(DataListFragment(DataClassHelper.CLASS_BORINGPICTURES))
        mFragments.add(DataListFragment(DataClassHelper.CLASS_DUANZI))
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
        mUi.viewpager.currentItem = HawkHelper.getTabSelection()
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.tablayout.setupWithViewPager(mUi.viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
        // 设置
            R.id.action_setting ->
                start(ARouter.getInstance()
                        .build(Module.MODULE_SETTING_PATH)
                        .navigation() as BaseFragment)
        }
        return true
    }

}