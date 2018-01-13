package cn.putong.home.ui

import android.support.v4.view.ViewPager
import cn.putong.home.HomePagerFragment
import cn.putong.home.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager

class HomePagerFragmentUi : AnkoComponent<HomePagerFragment> {
    lateinit var viewpager: ViewPager

    override fun createView(ui: AnkoContext<HomePagerFragment>) = with(ui) {
        frameLayout {
            viewpager = viewPager {
                id = R.id.home_viewpgaer
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}