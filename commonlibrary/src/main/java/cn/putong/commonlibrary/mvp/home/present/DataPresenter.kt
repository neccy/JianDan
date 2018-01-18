package cn.putong.commonlibrary.mvp.home.present

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.commonlibrary.mvp.home.biz.DataBiz
import cn.putong.commonlibrary.mvp.home.view.IDataView

/**
 * 数据Present
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
    fun getBoringPics() {
        dataView.showLoading()
        dataBiz.getBoringPics(dataView.getCurrentPage(), object : IBaseApiResultListener {
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
     * 获取妹子图数据列表
     */
    fun getMeiZiPics() {
        dataView.showLoading()
        dataBiz.getMeiZiPics(dataView.getCurrentPage(), object : IBaseApiResultListener {
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