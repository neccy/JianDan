package cn.putong.home.mvp.data.biz

import cn.putong.commonlibrary.base.IBaseApiResultListener

/**
 *
 * Created by xinyi on 2018/1/8.
 */
interface IDataBiz {

    fun getNewThings(page: Int, resultListener: IBaseApiResultListener)

    fun getBoringPictures(page: Int, resultListener: IBaseApiResultListener)

    fun getDuanZis(page: Int, resultListener: IBaseApiResultListener)
}