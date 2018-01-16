package cn.putong.gallery

import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.helper.ModuleHelper
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.frameLayout

/**
 * 画廊组件入口
 * Created by xinyi on 2018/1/16.
 */
@Route(path = ModuleHelper.GALLERY_MODULE_PATH)
class GalleryActivity : BaseActivity() {

    private lateinit var mPcis: ArrayList<String>

    override fun initUi() {
        frameLayout { id = R.id.gallery_fl }
    }

    override fun initData() {
        mPcis = intent.getStringArrayListExtra(ModuleHelper.PARAM_COMMENT_MODEL_PICS)
    }

    override fun initRootFragment() {
        if (findFragment(GalleryFragment::class.java) == null)
            loadRootFragment(R.id.gallery_fl, GalleryFragment(mPcis))
    }
}