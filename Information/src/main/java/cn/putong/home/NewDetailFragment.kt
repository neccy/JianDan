package cn.putong.home

import android.annotation.SuppressLint
import android.os.Bundle
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.home.mvp.data.model.NewModel
import kotlinx.android.synthetic.main.fragment_newdetail.*

/**
 * 新闻类型数据详情
 * Created by xinyi on 2018/1/10.
 */
@SuppressLint("ValidFragment")
class NewDetailFragment(private val mNewData: NewModel.Post) : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_newdetail)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initPicView()
    }

    private fun initPicView() {
        picview.setImageURI(mNewData.custom_fields.thumb_c[0])
    }
}