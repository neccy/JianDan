package cn.putong.home.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.putong.home.DataListFragment

/**
 * 数据列表Fragment适配器
 * Created by lala on 2018/1/7.
 */
class DataListFragmentAdapter(
        mFragmentManager: FragmentManager,
        private val mFragmentList: List<DataListFragment>,
        private val mTitles: Array<String>) :
        FragmentPagerAdapter(mFragmentManager) {

    override fun getItem(position: Int) = mFragmentList[position]

    override fun getCount() = mFragmentList.size

    override fun getPageTitle(position: Int) = mTitles[position]
}