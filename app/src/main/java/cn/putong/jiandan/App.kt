package cn.putong.jiandan

import cn.putong.commonlibrary.base.BaseApplication
import com.alibaba.android.arouter.launcher.ARouter


/**
 * App
 * Created by xinyi on 2018/1/6.
 */
class App : BaseApplication() {

    override fun initArouter() {
        super.initArouter()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }
}