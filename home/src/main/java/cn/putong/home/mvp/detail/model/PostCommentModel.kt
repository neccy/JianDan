package cn.putong.home.mvp.detail.model

/**
 * 新闻类型数据评论Model
 * Created by xinyi on 2018/1/12.
 */
class PostCommentModel {

    var status: String = ""
    var post: Post = Post()
    var next_url: String = ""

    class Post {
        var id: Int = 0
        var comments: List<Comment> = listOf()

        class Comment {
            var id: Int = 0
            var name: String = ""
            var url: String = ""
            var date: String = ""
            var content: String = ""
            var parent: Int = 0
            var vote_positive: Int = 0
            var vote_negative: Int = 0
            var index: Int = 0
        }
    }
}