package cn.putong.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.ui.PostDetailFragmentUi
import org.jetbrains.anko.AnkoContext

/**
 * Created by xinyi on 2018/1/13.
 */
class TestFragment : BaseFragment() {

    private val mUi = PostDetailFragmentUi()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)=
            mUi.createView(AnkoContext.Companion.create(context,this))
}