package cn.putong.commonlibrary.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import cn.putong.commonlibrary.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DataListFragmentUi<in T> : AnkoComponent<T> {

    lateinit var refresh: SwipeRefreshLayout
    lateinit var listview: RecyclerView
    lateinit var progressbar: ProgressBar

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        relativeLayout {
            refresh = swipeRefreshLayout {
                listview = recyclerView {
                    backgroundResource = R.color.recyclerview_bg_color
                    layoutManager = LinearLayoutManager(context)
                }
            }.lparams(width = matchParent, height = matchParent)

            progressbar = progressBar {
                visibility = View.GONE
            }.lparams {
                centerInParent()
            }
        }
    }

}