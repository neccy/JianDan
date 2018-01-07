package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import cn.putong.commonlibrary.base.BaseFragment

@SuppressLint("ValidFragment")
/**
 * 具体的数据界面
 * Created by lala on 2018/1/7.
 */
class DataListFragment(val mClass: Int) : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_datalist)
    }
}