package cn.putong.home

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.ui.PicViewFragmentUi
import me.relex.photodraweeview.PhotoDraweeView
import org.jetbrains.anko.AnkoContext

/**
 * 图片显示界面
 * Created by lala on 2018/1/14.
 */
@SuppressLint(value = ["ValidFragment"])
class PicViewFragment(
        private val mPics: List<String> = listOf()) : BaseFragment() {

    private lateinit var mUi: PicViewFragmentUi

    private lateinit var mPicViews: ArrayList<PhotoDraweeView>
    private lateinit var mPicViewAdapter: PicViewPagerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = PicViewFragmentUi()
    }

    override fun initData() {
        initPicViewList()
        initAdapter()
    }

    private fun initPicViewList() {
        mPicViews = arrayListOf()
        mPics.forEach {
            val mPicView = PhotoDraweeView(context)
            mPicView.setPhotoUri(Uri.parse(it))
            mPicViews.add(mPicView)
        }
    }

    private fun initAdapter() {
        mPicViewAdapter = PicViewPagerAdapter(mPicViews)
    }

    override fun initView() {
        initViewPager()
    }

    private fun initViewPager() {
        mUi.picviewpager.adapter = mPicViewAdapter
    }

    private class PicViewPagerAdapter(
            private var mPicViews: List<PhotoDraweeView> = listOf()) : PagerAdapter() {
        override fun isViewFromObject(view: View?, `object`: Any?) = view == `object`

        override fun getCount() = mPicViews.size

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            (container as ViewPager).addView(mPicViews[position])
            return mPicViews[position]
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            (container as ViewPager).removeView(mPicViews.get(position))
        }

        fun updateList(mPicViews: List<PhotoDraweeView>) {
            this.mPicViews = mPicViews
            notifyDataSetChanged()
        }
    }

}