package cn.putong.collection.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import cn.putong.collection.DataListFragment

/**
 * 数据列表Fragment适配器
 * Created by lala on 2018/1/7.
 */
class DataListFragmentAdapter(
        mFragmentManager: FragmentManager,
        private var mFragments: List<DataListFragment>,
        private var mTitles: Array<String?>) :
        FragmentPagerAdapter(mFragmentManager) {

    override fun getItem(position: Int) = mFragments[position]

    override fun getCount() = mFragments.size

    override fun getPageTitle(position: Int) = mTitles[position]

    /**
     * 重写此方法是防止notify无效
     */
    override fun getItemPosition(`object`: Any?): Int {
        return if (count == mTitles.size)
        // 如果当前数量跟标题数量相同，说明是添加了模版，不作处理
            super.getItemPosition(`object`)
        else
            PagerAdapter.POSITION_NONE
    }

    fun updateList(mFragments: List<DataListFragment>,
                   mTitles: Array<String?>) {
        this.mFragments = mFragments
        this.mTitles = mTitles
        notifyDataSetChanged()
    }

}