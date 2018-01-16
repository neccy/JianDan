package cn.putong.detail

import cn.putong.commonlibrary.base.BaseActivity
import cn.putong.commonlibrary.helper.DataClassHelper
import cn.putong.commonlibrary.helper.ModuleHelper
import cn.putong.commonlibrary.mvp.home.model.PostModel
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.frameLayout

/**
 * 详情组件入口
 * Created by lala on 2018/1/15.
 */
@Route(path = ModuleHelper.DETAIL_MOUDLE_PATH)
class DetailActivity : BaseActivity() {

    private var mClass = 0
    private var mPosition = 0
    private lateinit var mNewData: PostModel.Post

    override fun initUi() {
        frameLayout { id = R.id.detail_fl }
    }

    override fun initData() {
        mClass = intent.getIntExtra(ModuleHelper.PARAM_DATA_CLASS, mClass)
        mPosition = intent.getIntExtra(ModuleHelper.PARAM_POST_MODEL_POSITION, mPosition)
        mNewData = intent.getSerializableExtra(ModuleHelper.PARAM_POST_MODEL) as PostModel.Post
    }

    override fun initRootFragment() {
        if (mClass == DataClassHelper.CLASS_NEWTHINGS)
            if (findFragment(PostDetailFragment::class.java) == null)
                loadRootFragment(R.id.detail_fl, PostDetailFragment(mNewData, mPosition))
    }
}