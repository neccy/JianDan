package cn.putong.home.mvp.data.view

import cn.putong.commonlibrary.base.IBaseViewImpl

/**
 *
 * Created by xinyi on 2018/1/8.
 */
interface IDataView : IBaseViewImpl {

    /**
     * 获取当前页数
     */
    fun getCurrentPage(): Int

}