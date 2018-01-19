package cn.putong.web.ui

import android.support.v7.widget.Toolbar
import cn.putong.commonlibrary.R
import cn.putong.web.WebFragment
import org.jetbrains.anko.*

class WebFragmentUi : AnkoComponent<WebFragment> {

    lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<WebFragment>) = with(ui) {
        relativeLayout {
            toolbar = include<Toolbar>(R.layout.view_toolbar)
        }
    }
}