package cn.putong.home.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.putong.home.PostDetailFragment

/**
 * 新闻类型数据详情数据界面适配器
 * Created by xinyi on 2018/1/15.
 */
class PostDetialFragmentAdapter(
        fragmentManager: FragmentManager,
        private val mFragments: List<PostDetailFragment>) :
        FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = mFragments[position]

    override fun getCount() = mFragments.size
}