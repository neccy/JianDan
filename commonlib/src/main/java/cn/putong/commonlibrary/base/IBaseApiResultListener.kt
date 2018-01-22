package cn.putong.commonlibrary.base

/**
 *
 * Created by xinyi on 2018/1/8.
 */
interface IBaseApiResultListener {
    fun successful(model: Any)
    fun faild(msg: String)
}