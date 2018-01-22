package cn.putong.commonlibrary.mvp.home.model

import java.io.Serializable

/**
 * 新鲜事模型
 * Created by lala on 2018/1/8.
 */
class PostModel {

    var status: String = ""
    var count: Int = 0
    var count_total: Int = 0
    var pages: Int = 0
    var posts: List<Post> = listOf()

    class Post : Serializable {

        var id: Int = 0
        val url: String = ""
        val title: String = ""
        val excerpt: String = ""
        val date: String = ""
        val tags: List<Tag> = listOf()
        val author: Author = Author()
        val comment_count: Int = 0
        val comment_status: String = ""
        val custom_fields: CustomFields = CustomFields()
        var have_seen: Boolean = false

        class Tag : Serializable {
            val id: Int = 0
            val slug: String = ""
            val title: String = ""
            val description: String = ""
            val post_count: Int = 0
        }

        class Author : Serializable {
            val id: Int = 0
            val slug: String = ""
            val name: String = ""
            val first_name: String = ""
            val last_name: String = ""
            val nickname: String = ""
            val url: String = ""
            val description: String = ""
        }

        class CustomFields : Serializable {
            val thumb_c: ArrayList<String> = ArrayList()
        }
    }

}