package cn.putong.detail.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import cn.putong.detail.PostCommentFragment
import cn.putong.detail.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PostCommentFragmentUi : AnkoComponent<PostCommentFragment> {

    lateinit var toolbar: Toolbar
    lateinit var refresh: SwipeRefreshLayout
    lateinit var listview: RecyclerView

    override fun createView(ui: AnkoContext<PostCommentFragment>) = with(ui) {
        relativeLayout {
            appBarLayout {
                id = R.id.appbarlayout
                toolbar = include<Toolbar>(R.layout.view_toolbar)
            }.lparams(width = matchParent)
            refresh = swipeRefreshLayout {
                listview = recyclerView {
                    backgroundResource = R.color.recyclerview_bg_color
                    layoutManager = LinearLayoutManager(context)
                }
            }.lparams(width = matchParent, height = matchParent) {
                below(R.id.appbarlayout)
            }
        }
    }
}