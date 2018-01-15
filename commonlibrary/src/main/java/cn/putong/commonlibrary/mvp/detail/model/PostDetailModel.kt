package cn.putong.commonlibrary.mvp.detail.model

/**
 * 新闻类型数据详情Model
 * Created by xinyi on 2018/1/11.
 */
class PostDetailModel {

    var status: String = ""
    var post: Post = Post()
    var previous_url: String = ""
    var next_url: String = ""

    class Post {
        var id: Int = 0
        var content: String = ""
    }
}