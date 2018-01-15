package cn.putong.detail

import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.helper.ModuleHelper
import cn.putong.commonlibrary.otto.AppEvent
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.frameLayout

/**
 * 详情组件入口
 * Created by lala on 2018/1/15.
 */
@Route(path = ModuleHelper.DETAIL_MOUDLE_PATH)
class DetailActivity : BaseActivity() {

    override fun initUi() {
        frameLayout { id = R.id.detail_fl }
    }

    override fun initData() {
        val a = intent.getIntExtra(ModuleHelper.PARAM_POST_MODEL_POSITION, 0)
        val b = intent.getSerializableExtra(ModuleHelper.PARAM_POST_MODEL)
        AppEvent.post("a")
    }

    override fun initView() {
        initRootFragment()
    }

    private fun initRootFragment() {

    }

}