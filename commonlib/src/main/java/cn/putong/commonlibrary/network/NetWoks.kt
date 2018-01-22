package cn.putong.commonlibrary.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Retrofit封装
 * Created by lala on 2018/1/6.
 */
object NetWoks {

    private const val APIURL = "https://i.jandan.net"

    fun <T> configRetrofit(mService: Class<T>): T {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val retrofit = Retrofit.Builder().baseUrl(APIURL).
                addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(mService)
    }
}