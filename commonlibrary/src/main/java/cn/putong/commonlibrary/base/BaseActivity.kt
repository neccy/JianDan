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
        initUi()
        initData()
        initView()
        initListener()
        loadData()
    }

    override fun initUi() {}

    override fun initView() {}

    override fun initData() {}

    override fun initListener() {}

    override fun loadData() {}

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return FragmentAnimator(R.anim.admin_fragment_enter, R.anim.admin_fragment_exit,
                R.anim.admin_fragment_pop_enter, R.anim.admin_fragment_pop_exit)
    }

}