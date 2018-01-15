package cn.putong.jiandan

import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.helper.ModuleHelper
import com.alibaba.android.arouter.launcher.ARouter

class MainActivity : BaseActivity() {

    override fun initView() {
        super.initView()
        initHomeMoudle()
    }

    private fun initHomeMoudle() {
        ARouter.getInstance().build(ModuleHelper.HOME_MODULE_PATH).navigation();
    }
}
