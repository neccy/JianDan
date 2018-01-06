package cn.putong.commonlibrary.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.R
import me.yokeyword.fragmentation.SupportFragment

/**
 * Base Fragment
 * Created by xinyi on 2018/1/6.
 */
open class BaseFragment : SupportFragment(), IBaseImpl {

    private var mView: View? = null
    private var mContainer: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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

    override fun initView() {}

    override fun initData() {}

    override fun initListener() {}

    override fun loadData() {}

    /**
     * 设置ToolBar
     * @param mTitle 标题
     * @param mIsBack 是否设置返回按钮,默认为false
     */
    fun Toolbar.setToolbar(mTitle: String, mIsBack: Boolean = false) {
        title = mTitle
        (activity as BaseActivity).setSupportActionBar(this)
        if (mIsBack) {
            setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { pop() }
        }
    }

}