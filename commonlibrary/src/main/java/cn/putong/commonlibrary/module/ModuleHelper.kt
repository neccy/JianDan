package cn.putong.commonlibrary.module

import android.os.Bundle
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 组件方法工具类
 * Created by lala on 2018/1/17.
 */
object ModuleHelper {

    /**
     * 获取HomeFragment
     */
    fun geHomeFragment(): BaseFragment {
        return ARouter.getInstance()
                .build(Module.MODULE_HOME_PATH)
                .navigation() as BaseFragment
    }

    /**
     * 启动Post类型详情界面
     */
    fun startPosDetailtModule(fragment: BaseFragment, position: Int, postData: PostModel.Post) {
        val mPostFrtailFragment = ARouter.getInstance()
                .build(Module.MODULE_DETAIL_POST_PATH)
                .navigation() as BaseFragment

        val mBd = Bundle()
        mBd.putInt(Module.PARAM_POST_MODEL_POSITION, position)
        mBd.putSerializable(Module.PARAM_POST_MODEL, postData)
        mPostFrtailFragment.arguments = mBd
        fragment.start(mPostFrtailFragment)
    }

    /**
     * 启动Comment类型详情界面
     */
    fun startCommentDetailModule(fragment: BaseFragment, commentData: CommentModel.Comment) {
        val mCommentDetailFragment = ARouter.getInstance()
                .build(Module.MODULE_DETAIL_COMMENT_PATH)
                .navigation() as BaseFragment
        val mBd = Bundle()
        mBd.putSerializable(Module.PARAM_COMMENT_MODEL, commentData)
        mCommentDetailFragment.arguments = mBd
        fragment.start(mCommentDetailFragment)
    }

    /**
     * 启动画廊组件
     */
    fun startGalleryModule(fragment: BaseFragment, pics: ArrayList<String>) {
        val mGalleryFragment = ARouter.getInstance()
                .build(Module.MODULE_GALLERY_PATH)
                .navigation() as BaseFragment

        val mBd = Bundle()
        mBd.putStringArrayList(Module.PARAM_COMMENT_MODEL_PICS, pics)
        mGalleryFragment.arguments = mBd
        fragment.start(mGalleryFragment)
    }

    /**
     * 启动设置组件
     */
    fun startSetModule(fragment: BaseFragment) {
        fragment.start(ARouter.getInstance()
                .build(Module.MODULE_SETTING_PATH)
                .navigation() as BaseFragment)
    }

    /**
     * 启动Web组件
     */
    fun startWebModule(fragment: BaseFragment, url: String) {
        val mWebFragment = ARouter.getInstance()
                .build(Module.MODULE_WEB_PATH)
                .navigation() as BaseFragment

        val mBd = Bundle()
        mBd.putString(Module.PARAM_WEB_URL, url)
        mWebFragment.arguments = mBd
        fragment.start(mWebFragment)
    }

    /**
     * 启动收藏组件
     */
    fun startCollectionModule(fragment: BaseFragment) {
        val mCollectionFragment = ARouter.getInstance()
                .build(Module.MODULE_COLLECTION_PATH)
                .navigation() as BaseFragment
        fragment.start(mCollectionFragment)
    }

}