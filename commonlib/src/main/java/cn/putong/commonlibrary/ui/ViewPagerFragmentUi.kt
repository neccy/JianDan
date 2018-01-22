package cn.putong.commonlibrary.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import cn.putong.commonlibrary.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.viewPager

class ViewPagerFragmentUi<in T> : AnkoComponent<T> {

    lateinit var toolbar: Toolbar
    lateinit var tablayout: TabLayout
    lateinit var viewpager: ViewPager

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        coordinatorLayout {
            appBarLayout {
                toolbar = include<Toolbar>(R.layout.view_toolbar)
                        .lparams(width = matchParent) {
                            scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
                        }
                tablayout = include<TabLayout>(R.layout.view_tablayout)
            }.lparams(width = matchParent)

            relativeLayout {
                viewpager = viewPager {
                    id = R.id.viewpager
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}