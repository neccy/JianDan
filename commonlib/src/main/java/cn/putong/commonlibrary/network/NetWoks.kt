package cn.putong.commonlibrary.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Retrofit封装
 * Created by lala on 2018/1/6.
 */
object NetWoks {

    private const val JIANDAN_URL = "https://i.jandan.net"
    const val MOYU_URL = "https://api.moyu.today"

    fun <T> configRetrofit(mService: Class<T>, baseurl: String = JIANDAN_URL): T {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val retrofit = Retrofit
                .Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(mService)
    }
}