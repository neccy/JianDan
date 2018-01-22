package cn.putong.commonlibrary.base

/**
 * Base Activity or Fragment Impl
 * Created by xinyi on 2018/1/6.
 */
interface IBaseImpl {
    // 初始化Anko布局
    fun initUi()

    fun initView()
    fun initData()
    fun initListener()
    fun loadData()
}