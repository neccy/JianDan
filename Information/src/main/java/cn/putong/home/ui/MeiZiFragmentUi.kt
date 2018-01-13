package cn.putong.home.ui

import android.support.v7.widget.Toolbar
import cn.putong.home.MeiZiFragment
import cn.putong.home.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.include
import org.jetbrains.anko.linearLayout

class MeiZiFragmentUi : AnkoComponent<MeiZiFragment> {
    override fun createView(ui: AnkoContext<MeiZiFragment>) = with(ui) {
        linearLayout {
            include<Toolbar>(R.layout.view_toolbar)
        }
    }

}