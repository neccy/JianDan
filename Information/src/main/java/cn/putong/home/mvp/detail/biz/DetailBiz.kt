package cn.putong.home.mvp.detail.biz

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.commonlibrary.network.NetWoks
import cn.putong.home.api.ApiService
import cn.putong.home.mvp.detail.model.PostCommentModel
import cn.putong.home.mvp.detail.model.PostDetailModel
import retrofit2.Call
import retrofit2.Callback;
import retrofit2.Response

/**
 *
 * Created by xinyi on 2018/1/11.
 */
class DetailBiz : IDetailBiz {

    override fun getNewThingsDetail(id: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getNewThingsDetail(id).
                clone().enqueue(object : Callback<PostDetailModel> {

            override fun onResponse(call: Call<PostDetailModel>?, response: Response<PostDetailModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<PostDetailModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }

        })
    }

    override fun getNewThingsComments(id: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getNewThingsComments(id).
                clone().enqueue(object : Callback<PostCommentModel> {

            override fun onResponse(call: Call<PostCommentModel>?, response: Response<PostCommentModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<PostCommentModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }

        })
    }

}