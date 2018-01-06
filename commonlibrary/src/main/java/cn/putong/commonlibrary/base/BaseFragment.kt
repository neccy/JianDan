package cn.putong.commonlibrary.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
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
     * @param mTitle 标题内容
     * @param mIsBack 显示返回按钮并设置默认事件,默认为false
     */
    fun Toolbar.setToolbar(mTitle: String, mIsBack: Boolean = false) {
        //父界面
        val mActivity = (activity as BaseActivity)

        title = mTitle
        mActivity.setSupportActionBar(this)
        setHasOptionsMenu(true)
        if (mIsBack) {
            mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            setNavigationOnClickListener { pop() }
        }
    }

}