package cn.putong.commonlibrary.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit封装
 * Created by lala on 2018/1/6.
 */
object NetWoks {

    private val APIURL = "i.jandan.net"

    fun <T> configRetrofit(mService: Class<T>): T {
        val retrofit = Retrofit.Builder().baseUrl(APIURL).
                addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(mService)
    }
}