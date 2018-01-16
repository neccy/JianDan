package cn.putong.commonlibrary.api

import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel
import cn.putong.commonlibrary.mvp.detail.model.PostCommentModel
import cn.putong.commonlibrary.mvp.detail.model.PostDetailModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 首页模块-新鲜事接口
 * Created by lala on 2018/1/8.
 */
interface ApiService {

    /**
     * 获取新鲜事列表
     */
    @GET("/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page=?&custom_fields=thumb_c,views&dev=1")
    fun getNewThings(@Query("page") page: Int): Call<PostModel>

    /**
     * 获取新鲜事详情
     */
    @GET("?oxwlxojflwblxbsapi=get_post&include=content&id=")
    fun getNewThingsDetail(@Query("id") id: Int): Call<PostDetailModel>

    /**
     * 获取新鲜事评论列表
     */
    @GET(" /?oxwlxojflwblxbsapi=get_post&id=?&include=comments")
    fun getNewThingsComments(@Query("id") id: Int): Call<PostCommentModel>

    /**
     * 获取无聊图列表
     */
    @GET("/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=?")
    fun getBoringPictures(@Query("page") page: Int): Call<CommentModel>

    /**
     * 获取段子数据列表
     */
    @GET("/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=?")
    fun getDuanZis(@Query("page") page: Int): Call<CommentModel>

}