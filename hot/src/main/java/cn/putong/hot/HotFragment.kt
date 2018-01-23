package cn.putong.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.module.Module
import cn.putong.commonlibrary.ui.ViewPagerFragmentUi
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.AnkoContext

/**
 * 热榜组件入口
 * Created by xinyi on 2018/1/23.
 */
@Route(path = Module.MODULE_HOT_PATH)
class HotFragment : BaseFragment() {

    private lateinit var mUi: ViewPagerFragmentUi<HotFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = ViewPagerFragmentUi()
    }

}