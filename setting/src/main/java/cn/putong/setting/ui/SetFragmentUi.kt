package cn.putong.setting.ui

import android.support.v7.widget.Toolbar
import cn.putong.setting.R
import cn.putong.setting.SetFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout

class SetFragmentUi : AnkoComponent<SetFragment> {
    lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<SetFragment>) = with(ui) {
        verticalLayout {
            appBarLayout {
                toolbar = include<Toolbar>(R.layout.view_toolbar)
            }.lparams(width = matchParent)
            frameLayout {
                id = R.id.set_fl
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}