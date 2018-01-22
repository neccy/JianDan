package cn.putong.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.collection.adapter.DataListFragmentAdapter
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.module.Module
import cn.putong.commonlibrary.ui.ViewPagerFragmentUi
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.AnkoContext

/**
 * 收藏组件入口
 * Created by xinyi on 2018/1/22.
 */
@Route(path = Module.MODULE_COLLECTION_PATH)
class CollectionFragment : BaseFragment() {

    private lateinit var mUi: ViewPagerFragmentUi<CollectionFragment>

    private lateinit var mFragmentsAdapter: DataListFragmentAdapter
    private lateinit var mFragments: ArrayList<DataListFragment>
    private lateinit var mTemplates: Array<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
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
        mFragments = arrayListOf()
        mTemplates.forEachIndexed { index, _ ->
            mFragments.add(DataListFragment(index))
        }
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
        mUi.toolbar.setToolbar(getString(R.string.collection_toolbar_title), mIsBack = true)
    }

    private fun initTabLayout() {
        mTemplates.forEach {
            mUi.tablayout.addTab(mUi.tablayout.newTab().setText(it))
        }
    }

    private fun initViewPager() {
        mUi.viewpager.adapter = mFragmentsAdapter
        mUi.viewpager.offscreenPageLimit = mFragments.size - 1
        mUi.tablayout.setupWithViewPager(mUi.viewpager)
    }
}