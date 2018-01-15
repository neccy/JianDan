package cn.putong.home.mvp.detail.biz

import cn.putong.commonlibrary.base.IBaseApiResultListener

/**
 *
 * Created by xinyi on 2018/1/11.
 */
interface IDetailBiz {
    fun getNewThingsDetail(id: Int, resultListener: IBaseApiResultListener)

    fun getNewThingsComments(id: Int, resultListener: IBaseApiResultListener)
}