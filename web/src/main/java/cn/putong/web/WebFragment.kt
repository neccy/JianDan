package cn.putong.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.module.Module
import cn.putong.web.ui.WebFragmentUi
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.AnkoContext

/**
 * Web组件入口
 * Created by xinyi on 2018/1/19.
 */
@Route(path = Module.MODULE_WEB_PATH)
class WebFragment : BaseFragment() {

    private lateinit var mUi: WebFragmentUi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = WebFragmentUi()
    }

    override fun initData() {

    }

    override fun initView() {

    }

}