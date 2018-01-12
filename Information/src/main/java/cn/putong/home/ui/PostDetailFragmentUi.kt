package cn.putong.home.ui

import cn.putong.home.PostDetailFragment
import cn.putong.home.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class PostDetailFragmentUi : AnkoComponent<PostDetailFragment> {
    override fun createView(ui: AnkoContext<PostDetailFragment>) = with(ui) {
        relativeLayout {
            backgroundResource = R.color.post_detail_main_bg

            nestedScrollView {
                verticalLayout {

                }
            }.lparams(width = matchParent, height = matchParent) {
                topMargin = R.dimen.post_detail_toolbar_height
            }
        }
    }

}