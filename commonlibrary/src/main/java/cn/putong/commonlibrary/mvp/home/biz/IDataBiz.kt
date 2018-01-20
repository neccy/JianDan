package cn.putong.commonlibrary.mvp.home.biz

import cn.putong.commonlibrary.base.IBaseApiResultListener

/**
 *
 * Created by xinyi on 2018/1/8.
 */
interface IDataBiz {

    fun getNewThings(page: Int, resultListener: IBaseApiResultListener)

    fun getBoringPics(page: Int, resultListener: IBaseApiResultListener)

    fun getMeiZiPics(page: Int, resultListener: IBaseApiResultListener)

    fun getDuanZis(page: Int, resultListener: IBaseApiResultListener)

    fun positive(id:Int,resultListener: IBaseApiResultListener)
}