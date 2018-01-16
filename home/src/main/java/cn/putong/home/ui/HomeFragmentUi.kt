package cn.putong.home.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import cn.putong.home.HomeFragment
import cn.putong.home.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.viewPager

class HomeFragmentUi : AnkoComponent<HomeFragment> {

    lateinit var toolbar: Toolbar
    lateinit var tablayout: TabLayout
    lateinit var viewpager: ViewPager

    override fun createView(ui: AnkoContext<HomeFragment>) = with(ui) {
        coordinatorLayout {
            appBarLayout {
                toolbar = include<Toolbar>(R.layout.view_toolbar).
                        lparams(width = matchParent) {
                            scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
                        }
                tablayout = include<TabLayout>(R.layout.view_tablayout)
            }.lparams(width = matchParent)

            relativeLayout {
                viewpager = viewPager {
                    id = R.id.home_viewpgaer
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}