package cn.putong.commonlibrary.base

import java.util.*

/**
 *
 * Created by xinyi on 2018/1/8.
 */
interface IBaseApiResultListener {
    fun successful(model: Any)
    fun faild(msg: String)
}