package cn.putong.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.*
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.TemPlateHelper
import cn.putong.commonlibrary.helper.getMeiZiValue
import cn.putong.commonlibrary.module.Module
import cn.putong.commonlibrary.module.startCollectionModule
import cn.putong.commonlibrary.module.startHotMoudle
import cn.putong.commonlibrary.module.startSetModule
import cn.putong.commonlibrary.otto.AppEvent
import cn.putong.commonlibrary.otto.event.TemplateEvent
import cn.putong.commonlibrary.otto.event.UpdateDataEvent
import cn.putong.commonlibrary.ui.ViewPagerFragmentUi
import cn.putong.home.adapter.DataListFragmentAdapter
import cn.putong.home.helper.HawkHelper
import com.alibaba.android.arouter.facade.annotation.Route
import com.squareup.otto.Subscribe
import org.jetbrains.anko.AnkoContext

/**
 * 首页组件入口
 * Created by xinyi on 2018/1/6.
 */
@Route(path = Module.MODULE_HOME_PATH)
class HomeFragment : BaseFragment() {

    private lateinit var mUi: ViewPagerFragmentUi<HomeFragment>

    private lateinit var mFragmentsAdapter: DataListFragmentAdapter
    private lateinit var mFragments: ArrayList<DataListFragment>
    private lateinit var mTemplates: Array<String?>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun onDestroy() {
        super.onDestroy()
        HawkHelper.saveTabSelection(mUi.viewpager.currentItem)
    }

    override fun initUi() {
        mUi = ViewPagerFragmentUi()
    }

    override fun initData() {
        initTemplates()
        initFragments()
        initAdapter()
    }

    private fun initTemplates() {
        mTemplates = resources.getStringArray(R.array.template_item)
    }

    private fun initFragments() {
        mFragments = arrayListOf(
                DataListFragment(TemPlateHelper.NEWTHINGS),
                DataListFragment(TemPlateHelper.POPULAR),
                DataListFragment(TemPlateHelper.BORINGPICS),
                DataListFragment(TemPlateHelper.DUANZIS))

        if (getMeiZiValue(context))
            mFragments.add(DataListFragment(TemPlateHelper.MEIZIPICS))
    }

    private fun initAdapter() {
        mFragmentsAdapter = DataListFragmentAdapter(
                childFragmentManager, mFragments, mTemplates)
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
        mTemplates.forEach {
            mUi.tablayout.addTab(mUi.tablayout.newTab().setText(it))
        }
    }

    private fun initViewPager() {
        mUi.viewpager.adapter = mFragmentsAdapter
        mUi.viewpager.currentItem = HawkHelper.getTabSelection()
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.tablayout.setupWithViewPager(mUi.viewpager)
    }

    override fun initListener() {
        // Tab点击事件
        mUi.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                AppEvent.post(UpdateDataEvent())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_setting ->
                startSetModule()
            R.id.action_hot ->
                startHotMoudle()
            R.id.action_collection ->
                startCollectionModule()
        }
        return true
    }

    /**
     * 更新首页模版
     */
    @Subscribe
    fun updateTemplate(templateEvent: TemplateEvent) {
        val mCurrentItem: Int
        if (templateEvent.meizi_value) {
            mCurrentItem = 4
            mFragments.add(TemPlateHelper.MEIZIPICS,
                    DataListFragment(TemPlateHelper.MEIZIPICS))
        } else {
            mCurrentItem = 3
            mFragments.removeAt(TemPlateHelper.MEIZIPICS)
        }
        mFragmentsAdapter.updateList(mFragments, mTemplates)
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.viewpager.currentItem = mCurrentItem
    }

}