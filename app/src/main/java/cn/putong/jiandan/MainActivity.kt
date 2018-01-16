package cn.putong.jiandan

import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.module.Module
import com.alibaba.android.arouter.launcher.ARouter
import org.jetbrains.anko.frameLayout

class MainActivity : BaseActivity() {

    override fun initUi() {
        frameLayout { id = R.id.home_fl }
    }

    override fun initRootFragment() {
        val mHomeFragment = ARouter.getInstance()
                .build(Module.MODULE_HOME_PATH)
                .navigation() as BaseFragment
        loadRootFragment(R.id.home_fl, mHomeFragment)
    }

}
