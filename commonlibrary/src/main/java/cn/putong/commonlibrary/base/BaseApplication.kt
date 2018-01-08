package cn.putong.commonlibrary.base

import android.app.Application

/**
 * Base Application
 * Created by xinyi on 2018/1/6.
 */
open class BaseApplication : Application(), IBaseThreadLibsImpl {

    override fun onCreate() {
        super.onCreate()
        initThreadLibs()
    }

    private fun initThreadLibs() {
        initArouter()
        initSmartRefreshLsyout()
    }

    override fun initArouter() {}

    override fun initSmartRefreshLsyout() {}
}