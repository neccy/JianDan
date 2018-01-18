package cn.putong.home

import android.os.Bundle
import android.view.*
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.TemPlateHelper
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
        mUi = HomeFragmentUi()
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
                DataListFragment(TemPlateHelper.BORINGPICS),
                DataListFragment(TemPlateHelper.DUANZI),
                DataListFragment(TemPlateHelper.MEIZIPICS)
        )
        if (!getMeiZiValue(context))
            mFragments.removeAt(TemPlateHelper.MEIZIPICS)
    }

    private fun initAdapter() {
        mFragmentsAdapter =
                DataListFragmentAdapter(
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_setting ->
                ModuleHelper.startSetModule(fragment = this)
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
            mCurrentItem = 3
            mFragments.add(TemPlateHelper.MEIZIPICS,
                    DataListFragment(TemPlateHelper.MEIZIPICS))
        } else {
            mCurrentItem = 2
            mFragments.removeAt(TemPlateHelper.MEIZIPICS)
        }
        mFragmentsAdapter.updateList(mFragments, mTemplates)
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.viewpager.currentItem = mCurrentItem
    }

}