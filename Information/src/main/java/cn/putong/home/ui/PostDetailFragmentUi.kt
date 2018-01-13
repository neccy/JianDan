package cn.putong.home.ui

import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import cn.putong.commonlibrary.anko.simpleDraweeView
import cn.putong.home.PostDetailFragment
import cn.putong.home.R
import cn.putong.home.TestFragment
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class PostDetailFragmentUi : AnkoComponent<TestFragment> {

    lateinit var picview: SimpleDraweeView
    lateinit var post_title: TextView
    lateinit var author: TextView
    lateinit var time: TextView
    lateinit var excerpt: TextView
    lateinit var toolbar: Toolbar
    var webview: WebView? = null
    var progressbar: ProgressBar? = null

    override fun createView(ui: AnkoContext<TestFragment>) = with(ui) {
        relativeLayout {
            backgroundResource = R.color.post_detail_main_bg


                verticalLayout {

                    // 图片
                    picview = simpleDraweeView {

                    }.lparams(width = matchParent, height = R.dimen.post_detail_pic_height)

                    // 标题
                    post_title = textView {
                        gravity = Gravity.CENTER
                        textColor = Color.BLACK
                        textSize = R.dimen.textview_text_size_26sp.toFloat()
                        topPadding = R.dimen.post_detail_title_padding
                    }.lparams(width = matchParent) {
                        leftMargin = R.dimen.post_detail_title_horizontal_margin
                        rightMargin = R.dimen.post_detail_title_horizontal_margin
                        topMargin = R.dimen.post_detail_title_vertical_margin
                    }

                    // 作者信息
                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL
                        topPadding = R.dimen.post_detail_author_content_vertical_padding

                        author = textView {
                            textColor = Color.BLACK
                            textSize = R.dimen.textview_text_size_14sp.toFloat()
                        }.lparams {
                            rightMargin = R.dimen.post_detail_author_horizontal_margin
                        }

                        time = textView {
                            textSize = R.dimen.textview_text_size_14sp.toFloat()
                        }

                    }.lparams(width = matchParent)

                    // 摘要
                    excerpt = textView {
                        gravity = Gravity.CENTER
                        textSize = R.dimen.textview_text_size.toFloat()
                    }.lparams(width = matchParent).lparams(width = matchParent) {
                        leftMargin = R.dimen.post_detail_excerpt_horizontal_margin
                        rightMargin = R.dimen.post_detail_excerpt_horizontal_margin
                        topMargin = R.dimen.post_detail_excerpt_vertical_margin
                    }

                    // 线
                    textView {
                        gravity = Gravity.CENTER
                        backgroundResource = R.color.post_detail_lin_bg
                    }.lparams(width = R.dimen.post_detail_line_width,
                            height = R.dimen.post_detail_line_height) {
                        topMargin = R.dimen.post_detail_line_vertical_margin
                    }

                    // WebView
                    relativeLayout {

                        webview = webView {

                        }.lparams(width = matchParent, height = matchParent)

                        progressbar = progressBar {
                            visibility = View.GONE
                        }.lparams {
                            centerHorizontally()
                        }

                    }.lparams(width = matchParent, height = matchParent) {
                        topMargin = R.dimen.post_detail_webview_content_vertical_margin
                    }
                }


            linearLayout {
                orientation = LinearLayout.VERTICAL

                view {
                    backgroundResource = R.color.post_detail_toolbar_line
                }.lparams(width = matchParent, height = R.dimen.post_detail_line_height)

                toolbar = include<Toolbar>(R.layout.view_toolbar) {
                    backgroundResource = R.color.post_detail_toolbar_bg
                }

            }.lparams(width = matchParent, height = matchParent) {
                alignParentBottom()
            }
        }
    }

}