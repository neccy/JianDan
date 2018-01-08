package cn.putong.home.mvp.data.model

/**
 * 无聊图Model
 * Created by lala on 2018/1/8.
 */
class BoringPicturesModel {

    var status: String = ""
    var current_page: Int = 0
    var total_comments: Int = 0
    var page_count: Int = 0
    var count: Int = 0
    var comments: List<Comment> = listOf()

    class Comment {
        private var comment_ID: String = ""
        private var comment_post_ID: String = ""
        private var comment_author: String = ""
        private var comment_date: String = ""
        private var comment_date_gmt: String = ""
        private var comment_content: String = ""
        private var user_id: String = ""
        private var vote_positive: String = ""
        private var vote_negative: String = ""
        private var sub_comment_count: String = ""
        private var text_content: String = ""
        private var pics: List<String>? = listOf()
    }

}