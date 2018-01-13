package cn.putong.home.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.putong.commonlibrary.base.BaseFragment

/**
 * 首页Fragment适配器
 * Created by lala on 2018/1/14.
 */
class HomeFragmentAdapter(
        private val fragmentManager: FragmentManager,
        private val fragments: List<BaseFragment>) :
        FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}