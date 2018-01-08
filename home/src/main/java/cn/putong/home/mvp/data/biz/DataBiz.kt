package cn.putong.home.mvp.data.biz

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.commonlibrary.network.NetWoks
import cn.putong.home.api.ApiService
import cn.putong.home.mvp.data.model.BoringPicturesModel
import cn.putong.home.mvp.data.model.NewThingsModel
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
                clone().enqueue(object : Callback<NewThingsModel> {

            override fun onResponse(call: Call<NewThingsModel>?, response: Response<NewThingsModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<NewThingsModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }

        })
    }

    override fun getBoringPictures(page: Int, resultListener: IBaseApiResultListener) {
        NetWoks.configRetrofit(ApiService::class.java).getBoringPictures(page).
                clone().enqueue(object : Callback<BoringPicturesModel> {
            override fun onResponse(call: Call<BoringPicturesModel>?, response: Response<BoringPicturesModel>?) {
                resultListener.successful(response?.body()!!)
            }

            override fun onFailure(call: Call<BoringPicturesModel>?, t: Throwable?) {
                resultListener.faild(t?.message!!)
            }
        })
    }

}