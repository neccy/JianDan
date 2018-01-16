package cn.putong.detail.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import cn.putong.commonlibrary.anko.simpleDraweeView
import cn.putong.detail.PostDetailFragment
import cn.putong.detail.R
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class PostDetailFragmentUi : AnkoComponent<PostDetailFragment> {

    lateinit var picview: SimpleDraweeView
    lateinit var post_title: TextView
    lateinit var author: TextView
    lateinit var time: TextView
    lateinit var excerpt: TextView
    lateinit var toolbar: Toolbar
    var webview: WebView? = null
    var progressbar: ProgressBar? = null

    override fun createView(ui: AnkoContext<PostDetailFragment>) = with(ui) {
        relativeLayout {
            backgroundResource = R.color.post_detail_main_bg
            nestedScrollView {
                overScrollMode = ScrollView.OVER_SCROLL_NEVER
                verticalLayout {
                    // 图片
                    picview = simpleDraweeView().lparams(width = matchParent, height = dip(value = 200))

                    // 标题
                    post_title = textView {
                        gravity = Gravity.CENTER
                        textColor =
                                ContextCompat.getColor(context, R.color.textview_black_color)
                        textSize = 26f
                        topPadding = dip(value = 4)
                    }.lparams(width = matchParent) {
                        leftMargin = dip(value = 16)
                        rightMargin = dip(value = 16)
                        topMargin = dip(value = 32)
                    }

                    // 作者信息
                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL
                        topPadding = dip(value = 16)

                        author = textView {
                            textColor = Color.BLACK
                            textSize = 14f
                        }.lparams {
                            rightMargin = dip(value = 8)
                        }

                        time = textView {
                            textSize = 14f
                        }

                    }.lparams(width = matchParent)

                    // 摘要
                    excerpt = textView {
                        val add = 16f
                        val mult = 1f
                        setLineSpacing(add, mult)
                        gravity = Gravity.CENTER
                        textSize = 16f
                    }.lparams(width = matchParent).lparams(width = matchParent) {
                        leftMargin = dip(value = 32)
                        rightMargin = dip(value = 32)
                        topMargin = dip(value = 16)
                    }

                    // 线
                    textView {
                        backgroundResource = R.color.post_detail_lin_bg
                    }.lparams(width = dip(value = 100), height = dip(value = 1)) {
                        topMargin = dip(value = 32)
                        setHorizontalGravity(Gravity.CENTER)
                    }

                    // WebView
                    relativeLayout {

                        webview = webView().lparams(width = matchParent, height = matchParent)

                        progressbar = progressBar {
                            visibility = View.GONE
                        }.lparams {
                            centerHorizontally()
                        }

                    }.lparams(width = matchParent, height = matchParent) {
                        topMargin = dip(value = 16)
                    }
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(width = matchParent, height = matchParent)

            linearLayout {
                orientation = LinearLayout.VERTICAL
                view {
                    backgroundResource = R.color.post_detail_toolbar_line
                }.lparams(width = matchParent, height = dip(value = 1))

                toolbar = include<Toolbar>(R.layout.view_toolbar) {
                    backgroundResource = R.color.post_detail_toolbar_bg
                }.lparams(width = matchParent, height = dip(value = 47))

            }.lparams(width = matchParent, height = wrapContent) {
                alignParentBottom()
            }
        }
    }

}