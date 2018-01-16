package cn.putong.commonlibrary.mvp.detail.view

import cn.putong.commonlibrary.base.IBaseViewImpl

/**
 *
 * Created by xinyi on 2018/1/11.
 */
interface IDetailView : IBaseViewImpl{

    /**
     * 获取Id
     */
    fun getDataId() : Int
}