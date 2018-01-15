package cn.putong.commonlibrary.mvp.detail.presenter

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.commonlibrary.mvp.detail.biz.DetailBiz
import cn.putong.commonlibrary.mvp.detail.view.IDetailView

/**
 * 详情presenter
 * Created by xinyi on 2018/1/11.
 */
class DetailPresenter(IDetailView: IDetailView) {

    private val detailview = IDetailView
    private val detailBiz = DetailBiz()

    /**
     * 获取新鲜事详情
     */
    fun getNewThingsDetail() {
        detailview.showLoading()
        detailBiz.getNewThingsDetail(detailview.getDataId(), object : IBaseApiResultListener {
            override fun successful(model: Any) {
                detailview.successful(model)
                detailview.hideLoading()
            }

            override fun faild(msg: String) {
                detailview.error(msg)
                detailview.hideLoading()
            }
        })
    }

    /**
     * 获取新鲜事评论信息
     */
    fun getNewThingsComments() {
        detailview.showLoading()
        detailBiz.getNewThingsComments(detailview.getDataId(), object : IBaseApiResultListener {
            override fun successful(model: Any) {
                detailview.successful(model)
                detailview.hideLoading()
            }

            override fun faild(msg: String) {
                detailview.error(msg)
                detailview.hideLoading()
            }
        })
    }
}