package cn.putong.commonlibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment

/**
 * Base Fragment
 * Created by xinyi on 2018/1/6.
 */
open class BaseFragment : SupportFragment(), IBaseImpl {

    private var mView: View? = null
    private var mContainer: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.mContainer = container!!
        return mView!!
    }

    fun setContentView(viewId: Int) {
        mView = layoutInflater.inflate(viewId, mContainer, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
        loadData()
    }

    fun getContentView(): View = mView!!

    override fun initView() {}

    override fun initData() {}

    override fun initListener() {}

    override fun loadData() {}

}