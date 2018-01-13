package cn.putong.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.ui.MeiZiFragmentUi
import org.jetbrains.anko.AnkoContext

class MeiZiFragment : BaseFragment() {

    private lateinit var mUi : MeiZiFragmentUi

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context,this))
    }

    override fun initUi() {
        mUi = MeiZiFragmentUi()
    }
}