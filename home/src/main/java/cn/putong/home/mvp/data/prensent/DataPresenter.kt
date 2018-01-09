package cn.putong.home.mvp.data.prensent

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.home.mvp.data.biz.DataBiz
import cn.putong.home.mvp.data.view.IDataView

/**
 * 首页数据Present
 * Created by xinyi on 2018/1/8.
 */
class DataPresenter(IDataView: IDataView) {

    private val dataView = IDataView
    private val dataBiz = DataBiz()

    /**
     * 获取新鲜事数据列表
     */
    fun getNewThings() {
        dataView.showLoading()
        dataBiz.getNewThings(dataView.getCurrentPage(), object : IBaseApiResultListener {
            override fun successful(model: Any) {
                dataView.hideLoading()
                dataView.successful(model)
            }

            override fun faild(msg: String) {
                dataView.error(msg)
                dataView.hideLoading()
            }
        })
    }

    /**
     * 获取无聊图数据列表
     */
    fun getBoringPictures() {
        dataView.showLoading()
        dataBiz.getBoringPictures(dataView.getCurrentPage(), object : IBaseApiResultListener {
            override fun successful(model: Any) {
                dataView.hideLoading()
                dataView.successful(model)
            }

            override fun faild(msg: String) {
                dataView.error(msg)
                dataView.hideLoading()
            }
        })
    }

    /**
     * 获取段子数据列表
     */
    fun getDuanZis() {
        dataView.showLoading()
        dataBiz.getDuanZis(dataView.getCurrentPage(), object : IBaseApiResultListener {
            override fun successful(model: Any) {
                dataView.hideLoading()
                dataView.successful(model)
            }

            override fun faild(msg: String) {
                dataView.error(msg)
                dataView.hideLoading()
            }
        })
    }
}