package cn.putong.home

import android.os.Bundle
import android.support.v7.widget.Toolbar
import cn.putong.commonlibrary.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 首页
 * Created by xinyi on 2018/1/6.
 */
class HomeFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
    }

    override fun initView() {
        super.initView()
        initToolBar()
        initTabLayout()
    }

    private fun initToolBar() {
        (toolbar as Toolbar).setToolbar(getString(R.string.app_name))
    }

    private fun initTabLayout() {

    }

    override fun initData() {
        super.initData()
    }
}