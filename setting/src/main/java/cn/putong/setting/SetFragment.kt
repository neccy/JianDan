package cn.putong.setting

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.module.Module
import cn.putong.setting.ui.SetFragmentUi
import com.alibaba.android.arouter.facade.annotation.Route
import org.jetbrains.anko.AnkoContext

/**
 * 设置页面
 * Created by xinyi on 2018/1/16.
 */
@Route(path = Module.MODULE_SETTING_PATH)
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
        initToolbar()
        initPreference()
    }

    private fun initToolbar() {
        mUi.toolbar.setToolbar(getString(R.string.set_toolbar_title), mIsBack = true)
    }

    private fun initPreference() {
        activity.fragmentManager
                .beginTransaction()
                .replace(R.id.set_fl, SetPreferenceFragment())
                .commit()
    }

    class SetPreferenceFragment : PreferenceFragment() {

        // 妹子图
        private lateinit var key_preference_meizi: Preference
        // 不受欢迎内容
        private lateinit var key_preference_unwelcome: Preference
        // 动画自动播放
        private lateinit var key_preference_gifautoplay: Preference
        // 使用Chrome打开链接
        private lateinit var key_preference_usechrome: Preference
        // 游客账号
        private lateinit var key_preference_account: Preference
        // 关于
        private lateinit var key_preference_about: Preference
        // 版本
        private lateinit var key_preference_version: Preference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences_set)
            initView()
            initData()
        }

        private fun initView() {
            key_preference_meizi = preferenceManager
                    .findPreference(R.string.key_preference_meizi.toString())
            key_preference_unwelcome = preferenceManager
                    .findPreference(R.string.key_preference_unwelcome.toString())
            key_preference_gifautoplay = preferenceManager
                    .findPreference(R.string.key_preference_gifautoplay.toString())
            key_preference_usechrome = preferenceManager
                    .findPreference(R.string.key_preference_usechrome.toString())
            key_preference_account = preferenceManager
                    .findPreference(R.string.key_preference_account.toString())
            key_preference_about = preferenceManager
                    .findPreference(R.string.key_preference_about.toString())
            key_preference_version = preferenceManager
                    .findPreference(R.string.key_preference_version.toString())
        }

        private fun initData() {

        }
    }

}