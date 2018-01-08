package cn.putong.home.api

import cn.putong.home.mvp.data.model.NewThingsModel
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
    fun getNewThings(@Query("page") page: Int): Call<NewThingsModel>

}