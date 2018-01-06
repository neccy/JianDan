package cn.putong.jiandan

import android.os.Bundle
import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.util.ModuleUtil
import com.alibaba.android.arouter.launcher.ARouter

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun initView() {
        super.initView()
        initHomeMoudle()
    }

    private fun initHomeMoudle() {
        ARouter.getInstance().build(ModuleUtil.HOME_MODULE_PATH).navigation();
    }
}
