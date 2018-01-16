package cn.putong.jiandan

import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.module.ModuleHelper
import org.jetbrains.anko.frameLayout

class MainActivity : BaseActivity() {

    override fun initUi() {
        frameLayout { id = R.id.home_fl }
    }

    override fun initRootFragment() {
        loadRootFragment(R.id.home_fl, ModuleHelper.geHomeFragment())
    }

}
