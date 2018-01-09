package cn.putong.jiandan

import cn.putong.commonlibrary.base.BaseApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.drawee.backends.pipeline.Fresco


/**
 * App
 * Created by xinyi on 2018/1/6.
 */
class App : BaseApplication() {

    override fun initArouter() {
        super.initArouter()
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }

    override fun initFresco() {
        super.initFresco()
        Fresco.initialize(this)
    }
}