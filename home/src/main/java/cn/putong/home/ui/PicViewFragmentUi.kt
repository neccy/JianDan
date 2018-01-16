package cn.putong.home.ui

import android.support.v4.view.ViewPager
import cn.putong.home.PicViewFragment
import cn.putong.home.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

class PicViewFragmentUi : AnkoComponent<PicViewFragment> {

    lateinit var picviewpager: ViewPager

    override fun createView(ui: AnkoContext<PicViewFragment>) = with(ui) {
        linearLayout {
            picviewpager = viewPager {
                backgroundResource = R.color.colorPrimary
                id = R.id.home_viewpgaer
            }.lparams(width = matchParent, height = matchParent)
        }
    }

}