package cn.putong.commonlibrary.base

import java.util.*

/**
 * mvp base 接口
 * Created by xinyi on 2018/1/8.
 */
interface IBaseViewImpl {
    fun showLoading()
    fun hideLoading()
    fun successful(model:Any)
    fun error(msg: String)
}