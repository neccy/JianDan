package cn.putong.home.mvp.data.biz

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.commonlibrary.network.NetWoks
import cn.putong.home.api.ApiService
import cn.putong.home.mvp.data.model.CardModel
import cn.putong.home.mvp.data.model.NormalModel
import retrofit2.Call
import retrofit2.Callback;
import retrofit2.Response

/**
 *
 * Created by xinyi on 2018/1/8.
 */
class DataBiz : IDataBiz {

    override fun getNewThings(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getNewThings(page).
                clone().enqueue(object : Callback<NormalModel> {

            override fun onResponse(call: Call<NormalModel>?, response: Response<NormalModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<NormalModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }

        })
    }

    override fun getBoringPictures(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getBoringPictures(page).
                clone().enqueue(object : Callback<CardModel> {
            override fun onResponse(call: Call<CardModel>?, response: Response<CardModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<CardModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

    override fun getDuanZis(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getDuanZis(page).
                clone().enqueue(object : Callback<CardModel> {
            override fun onResponse(call: Call<CardModel>?, response: Response<CardModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<CardModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

}