package cn.putong.commonlibrary.module

import android.os.Bundle
import cn.putong.commonlibrary.base.BaseFragment
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
     * 获取Post类型详情界面
     */
    fun startPosDetailtModule(fragment: BaseFragment, position: Int, mPostData: PostModel.Post) {
        val mPostFrtailFragment = ARouter.getInstance()
                .build(Module.MODULE_DETAIL_POST_PATH)
                .navigation() as BaseFragment

        val mBd = Bundle()
        mBd.putInt(Module.PARAM_POST_MODEL_POSITION, position)
        mBd.putSerializable(Module.PARAM_POST_MODEL, mPostData)
        mPostFrtailFragment.arguments = mBd
        fragment.start(mPostFrtailFragment)
    }

    /**
     * 获取画廊界面
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

}