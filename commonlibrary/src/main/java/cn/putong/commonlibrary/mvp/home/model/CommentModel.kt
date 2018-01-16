package cn.putong.commonlibrary.mvp.home.model

import java.util.ArrayList

/**
 * 卡片类型视图Model
 * Created by lala on 2018/1/8.
 */
class CommentModel {

    var status: String = ""
    var current_page: Int = 0
    var total_comments: Int = 0
    var count: Int = 0
    var comments: List<Comment> = listOf()

    class Comment {
        var comment_ID: String = ""
        var comment_post_ID: String = ""
        var comment_author: String = ""
        var comment_date: String = ""
        var comment_date_gmt: String = ""
        var comment_content: String = ""
        var user_id: String = ""
        var vote_positive: String = ""
        var vote_negative: String = ""
        var sub_comment_count: String = ""
        var text_content: String = ""
        var pics: ArrayList<String> = ArrayList()
    }

}