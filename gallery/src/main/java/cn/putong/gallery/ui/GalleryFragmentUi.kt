package cn.putong.gallery.ui

import android.support.v4.view.ViewPager
import cn.putong.gallery.GalleryFragment
import cn.putong.gallery.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

class GalleryFragmentUi : AnkoComponent<GalleryFragment> {

    lateinit var picviewpager: ViewPager

    override fun createView(ui: AnkoContext<GalleryFragment>) = with(ui) {
        linearLayout {
            picviewpager = viewPager {
                backgroundResource = R.color.colorPrimary
                id = R.id.viewpager
            }.lparams(width = matchParent, height = matchParent)
        }
    }

}