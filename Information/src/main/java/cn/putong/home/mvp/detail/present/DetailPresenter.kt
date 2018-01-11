package cn.putong.home.mvp.detail.present

import cn.putong.commonlibrary.base.IBaseApiResultListener
import cn.putong.home.mvp.detail.biz.DetailBiz
import cn.putong.home.mvp.detail.view.IDetailView

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
}