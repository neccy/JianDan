package cn.putong.gallery

import android.net.Uri
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.FrescoHelper
import cn.putong.commonlibrary.module.Module
import cn.putong.gallery.ui.GalleryFragmentUi
import com.alibaba.android.arouter.facade.annotation.Route
import me.relex.photodraweeview.PhotoDraweeView
import org.jetbrains.anko.AnkoContext

/**
 * 画廊界面
 * Created by xinyi on 2018/1/16.
 */
@Route(path = Module.MODULE_GALLERY_PATH)
class GalleryFragment() : BaseFragment() {

    private lateinit var mUi: GalleryFragmentUi

    private lateinit var mPicViews: ArrayList<PhotoDraweeView>
    private lateinit var mPicViewAdapter: PicViewPagerAdapter

    private lateinit var mPics: ArrayList<String>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = GalleryFragmentUi()
    }

    override fun initData() {
        initArgumentData()
        initPicViewList()
        initAdapter()
    }

    private fun initArgumentData() {
        mPics = arguments.getStringArrayList(Module.PARAM_COMMENT_MODEL_PICS)
    }

    private fun initPicViewList() {
        mPicViews = arrayListOf()
        mPics.forEach {
            val mPicView = PhotoDraweeView(context)
            FrescoHelper.setAnimatorController(Uri.parse(it), mPicView)
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