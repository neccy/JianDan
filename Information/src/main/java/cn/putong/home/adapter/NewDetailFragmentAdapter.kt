package cn.putong.home.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.putong.home.NewDetailFragment

/**
 * 新闻类型数据Fragment适配器
 * Created by xinyi on 2018/1/10.
 */
class NewDetailFragmentAdapter(
        mFragmentManager: FragmentManager,
        private val mFragments: List<NewDetailFragment>) : FragmentPagerAdapter(mFragmentManager) {

    override fun getItem(position: Int) = mFragments[position]

    override fun getCount() = mFragments.size
}