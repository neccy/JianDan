package cn.putong.home.api

import cn.putong.home.mvp.data.model.CardModel
import cn.putong.home.mvp.data.model.NormalModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 首页模块-新鲜事接口
 * Created by lala on 2018/1/8.
 */
interface ApiService {

    /**
     * 根据页数获取新鲜事列表
     */
    @GET("/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page=?&custom_fields=thumb_c,views&dev=1")
    fun getNewThings(@Query("page") page: Int): Call<NormalModel>

    /**
     * 根据页数获取无聊图列表
     */
    @GET("/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=?")
    fun getBoringPictures(@Query("page") page: Int): Call<CardModel>

    /**
     * 获取段子数据列表
     */
    @GET("/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=?")
    fun getDuanZis(@Query("page") page: Int): Call<CardModel>
}