package cn.putong.setting

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.helper.ModuleHelper
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout

/**
 * 设置页面
 * Created by xinyi on 2018/1/16.
 */
@Route(path = ModuleHelper.SETTING_MOUDLE_PATH)
class SetFragment : BaseFragment() {

    private lateinit var mUi: SetFragmentUi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return mUi.createView(AnkoContext.Companion.create(context, owner = this))
    }

    override fun initUi() {
        mUi = SetFragmentUi()
    }

    override fun initView() {
        activity.fragmentManager.beginTransaction().replace(R.id.set_fl, SetPreferenceFragment())
    }

    class SetFragmentUi : AnkoComponent<SetFragment> {
        override fun createView(ui: AnkoContext<SetFragment>) = with(ui) {
            frameLayout {
                id = R.id.set_fl
            }
        }

    }

    class SetPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences_set)
        }
    }

}