package cn.putong.home.mvp.detail.biz

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.commonlibrary.network.NetWoks
import cn.putong.home.api.ApiService
import cn.putong.home.mvp.detail.model.NewDetailModel
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
                clone().enqueue(object : Callback<NewDetailModel> {

            override fun onResponse(call: Call<NewDetailModel>?, response: Response<NewDetailModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<NewDetailModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }

        })
    }
}