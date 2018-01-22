package cn.putong.collection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.ui.DataListFragmentUi
import org.jetbrains.anko.AnkoContext

/**
 * 数据列表界面
 */
@SuppressLint(value = ["ValidFragment"])
class DataListFragment(private val mTemPlate: Int) : BaseFragment() {

    private lateinit var mUi: DataListFragmentUi<DataListFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = DataListFragmentUi()
    }

    override fun initData() {
        super.initData()
    }
}