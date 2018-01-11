package cn.putong.commonlibrary.base

import android.os.Bundle
import cn.putong.commonlibrary.R
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * BaseActivity
 * Created by xinyi on 2018/1/6.
 */
open class BaseActivity : SupportActivity(), IBaseImpl {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initView()
        initData()
        initListener()
        loadData()
    }

    override fun initView() {}

    override fun initData() {}

    override fun initListener() {}

    override fun loadData() {}
}