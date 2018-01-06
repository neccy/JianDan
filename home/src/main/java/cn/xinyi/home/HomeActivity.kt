package cn.xinyi.home

import android.os.Bundle
import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.util.ModuleUtil
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * 首页组件入口
 * Created by xinyi on 2018/1/6.
 */
@Route(path = ModuleUtil.HOME_MODULE_PATH)
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun initView() {
        super.initView()
        initRootFragment()
    }

    private fun initRootFragment() {
        if (findFragment(HomeFragment::class.java) == null)
            loadRootFragment(R.id.home_fl, HomeFragment())
    }
}