package cn.putong.home

import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.helper.ModuleHelper
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.frameLayout

/**
 * 首页组件入口
 * Created by xinyi on 2018/1/6.
 */
@Route(path = ModuleHelper.HOME_MODULE_PATH)
class HomeActivity : BaseActivity() {

    override fun initUi() {
        frameLayout { id = R.id.home_fl }
    }

    override fun initRootFragment() {
        if (findFragment(HomeFragment::class.java) == null)
            loadRootFragment(R.id.home_fl, HomeFragment())
    }
}