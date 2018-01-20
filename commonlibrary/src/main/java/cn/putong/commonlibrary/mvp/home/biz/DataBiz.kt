package cn.putong.commonlibrary.mvp.home.biz

import cn.putong.commonlibrary.api.ApiService
import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel
import cn.putong.commonlibrary.network.NetWoks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * Created by xinyi on 2018/1/8.
 */
class DataBiz : IDataBiz {

    override fun getNewThings(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getNewThings(page)
                .clone().enqueue(object : Callback<PostModel> {

            override fun onResponse(call: Call<PostModel>?, response: Response<PostModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<PostModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

    override fun getBoringPics(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getBoringPictures(page)
                .clone().enqueue(object : Callback<CommentModel> {
            override fun onResponse(call: Call<CommentModel>?, response: Response<CommentModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<CommentModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

    override fun getMeiZiPics(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getMeiZiPics(page)
                .clone().enqueue(object : Callback<CommentModel> {
            override fun onResponse(call: Call<CommentModel>?, response: Response<CommentModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<CommentModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

    override fun getDuanZis(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getDuanZis(page)
                .clone().enqueue(object : Callback<CommentModel> {
            override fun onResponse(call: Call<CommentModel>?, response: Response<CommentModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<CommentModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

    override fun positive(id: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).positive(id)
                .clone().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

}