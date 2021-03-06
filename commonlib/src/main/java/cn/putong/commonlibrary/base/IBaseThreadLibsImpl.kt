package cn.putong.commonlibrary.base

/**
 * 第三方框架实现接口
 * Created by xinyi on 2018/1/6.
 */
interface IBaseThreadLibsImpl {

    /**
     * 初始化路由
     */
    fun initArouter()

    /**
     * 初始化Fresco
     */
    fun initFresco()

    /**
     * 初始化数据库
     */
    fun initRealm()

    /**
     * 初始化Hawk(键值对库)
     */
    fun initHawk()

}