package cn.putong.commonlibrary.api

import cn.putong.commonlibrary.mvp.detail.model.PostCommentModel
import cn.putong.commonlibrary.mvp.detail.model.PostDetailModel
import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel
import retrofit2.Call
import retrofit2.http.*

/**
 * 首页模块-新鲜事接口
 * Created by lala on 2018/1/8.
 */
interface ApiService {

    /**
     * 获取新鲜事列表
     */
    @GET(value = "/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date," +
            "tags,author,title,excerpt,comment_count,comment_status," +
            "custom_fields&page=?&custom_fields=thumb_c,views&dev=1")
    fun getNewThings(@Query(value = "page") page: Int): Call<PostModel>

    /**
     * 获取新鲜事详情
     */
    @GET(value = "?oxwlxojflwblxbsapi=get_post&include=content&id=")
    fun getNewThingsDetail(@Query(value = "id") id: Int): Call<PostDetailModel>

    /**
     * 获取新鲜事评论列表
     */
    @GET(value = " /?oxwlxojflwblxbsapi=get_post&id=?&include=comments")
    fun getNewThingsComments(@Query(value = "id") id: Int): Call<PostCommentModel>

    /**
     * 获取无聊图列表
     */
    @GET(value = "/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=?")
    fun getBoringPictures(@Query(value = "page") page: Int): Call<CommentModel>

    /**
     * 获取妹子图列表
     */
    @GET(value = "/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=?")
    fun getMeiZiPics(@Query(value = "page") page: Int): Call<CommentModel>

    /**
     * 获取段子数据列表
     */
    @GET(value = "/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=?")
    fun getDuanZis(@Query(value = "page") page: Int): Call<CommentModel>

    /**
     * 点赞
     */
    @FormUrlEncoded
    @POST(value = "/index.php?acv_ajax=true&option=1")
    fun positive(@Field(value = "id") id: Int): Call<String>

    /**
     * 讨厌
     */
    @FormUrlEncoded
    @POST(value = "/index.php?acv_ajax=true&option=0")
    fun negative(@Field(value = "id") id: Int): Call<String>
}