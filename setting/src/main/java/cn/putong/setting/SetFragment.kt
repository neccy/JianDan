package cn.putong.setting

import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.base.BasePreferenceFragment
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

    class SetPreferenceFragment : BasePreferenceFragment() {

        private lateinit var preference_meizi: Preference
        private lateinit var preference_unwelcome: Preference
        private lateinit var preference_gifautoplay: ListPreference
        private lateinit var preference_usechrome: Preference
        private lateinit var preference_account: Preference
        private lateinit var preference_about: Preference
        private lateinit var preference_version: Preference

        override fun initResource() {
            addPreferencesFromResource(R.xml.preferences_set)
        }

        override fun initView() {
            preference_meizi = preferenceManager
                    .findPreference(getString(R.string.key_preference_meizi))
            preference_unwelcome = preferenceManager
                    .findPreference(getString(R.string.key_preference_unwelcome))
            preference_gifautoplay = preferenceManager
                    .findPreference(getString(R.string.key_preference_gifautoplay))
                    as ListPreference
            preference_usechrome = preferenceManager
                    .findPreference(getString(R.string.key_preference_usechrome))
            preference_account = preferenceManager
                    .findPreference(getString(R.string.key_preference_account))
            preference_about = preferenceManager
                    .findPreference(getString(R.string.key_preference_about))
            preference_version = preferenceManager
                    .findPreference(getString(R.string.key_preference_version))
        }

        override fun initData() {
            initGifAutoPlayer()
        }

        /**
         * 初始化动画自动播放偏好
         */
        private fun initGifAutoPlayer() {
            preference_gifautoplay.summary =
                    getGifAutoPlaySummary(preference_gifautoplay.value)
        }

        /**
         * 获取动画自动播放偏好Summary
         */
        private fun getGifAutoPlaySummary(value: String): String {
            val mEntries = resources
                    .getStringArray(R.array.preference_gifautoplay_entries)
            return mEntries[value.toInt()]
        }

        override fun initListener() {
            preference_gifautoplay.setOnPreferenceChangeListener { preference, newValue ->
                preference_gifautoplay.summary = getGifAutoPlaySummary(newValue.toString())
                preference_gifautoplay.setValueIndex(newValue.toString().toInt())
                super.onPreferenceTreeClick(preferenceScreen, preference)
            }
        }

    }

}