package cn.putong.home

import android.os.Bundle
import android.view.*
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.DataClassHelper
import cn.putong.commonlibrary.helper.getMeiZiValue
import cn.putong.commonlibrary.module.Module
import cn.putong.commonlibrary.module.ModuleHelper
import cn.putong.commonlibrary.otto.event.TemplateEvent
import cn.putong.home.adapter.DataListFragmentAdapter
import cn.putong.home.helper.HawkHelper
import cn.putong.home.ui.HomeFragmentUi
import com.alibaba.android.arouter.facade.annotation.Route
import com.squareup.otto.Subscribe
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
    private lateinit var mClassItems: Array<String?>

    private var mMeiZiValue: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            mUi.createView(AnkoContext.Companion.create(context, owner = this))

    override fun onDestroy() {
        super.onDestroy()
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
        mMeiZiValue = getMeiZiValue(context)
        mClassItems = arrayOfNulls<String>(size = 4)
        mClassItems[0] = getString(R.string.home_template_newthings)
        mClassItems[1] = getString(R.string.home_template_boringpics)
        mClassItems[2] =
                if (mMeiZiValue)
                    getString(R.string.home_template_meizipics)
                else
                    getString(R.string.home_template_duanzis)
        if (mMeiZiValue)
            mClassItems[3] = getString(R.string.home_template_duanzis)
    }

    private fun initFragmentList() {
        mFragments = ArrayList()
        mFragments.add(DataListFragment(DataClassHelper.CLASS_NEWTHINGS))
        mFragments.add(DataListFragment(DataClassHelper.CLASS_BORINGPICS))
        if (mMeiZiValue)
            mFragments.add(DataListFragment(DataClassHelper.CLASS_MEIZIPICS))
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
            R.id.action_setting ->
                // 设置
                ModuleHelper.startSetModule(fragment = this)
        }
        return true
    }

    /**
     * 更新首页模版
     */
    @Subscribe
    fun updateTemplate(templateEvent: TemplateEvent) {
        if (templateEvent.meizi_value) {
            mClassItems[2] = getString(R.string.home_template_meizipics)
            mClassItems[3] = getString(R.string.home_template_duanzis)
            mFragments[2] = DataListFragment(DataClassHelper.CLASS_MEIZIPICS)
            mFragments.add(3, DataListFragment(DataClassHelper.CLASS_DUANZI))
        } else {
            mClassItems[2] = getString(R.string.home_template_duanzis)
            mFragments[2] = DataListFragment(DataClassHelper.CLASS_BORINGPICS)
            mFragments.removeAt(2)
        }
        mFragmentsAdapter.notifyDataSetChanged()
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.viewpager.currentItem = 2
    }

}