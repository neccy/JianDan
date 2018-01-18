package cn.putong.setting

import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.SwitchPreference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseFragment
import cn.putong.commonlibrary.base.BasePreferenceFragment
import cn.putong.commonlibrary.module.Module
import cn.putong.commonlibrary.otto.AppEvent
import cn.putong.commonlibrary.otto.event.TemplateEvent
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

        private lateinit var preference_meizi: SwitchPreference
        private lateinit var preference_unwelcome: Preference
        private lateinit var preference_gifautoplay: ListPreference
        private lateinit var preference_usechrome: Preference
        private lateinit var preference_account: Preference
        private lateinit var preference_about: Preference
        private lateinit var preference_version: Preference

        override fun initResource() {
            addPreferencesFromResource(R.xml.preferences_set)
        }

        override fun initPreference() {
            preference_meizi = preferenceManager
                    .findPreference(getString(R.string.key_preference_meizi))
                    as SwitchPreference
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

        override fun initConfig() {
            initAutoPlayerPrefConfig()
            initMeiZiPrefConfig()
        }

        /**
         * 初始化动画自动播放偏好
         */
        private fun initAutoPlayerPrefConfig() {
            preference_gifautoplay.summary =
                    getGifAutoPlaySummary(preference_gifautoplay.value)
        }

        /**
         * 初始化妹子开关偏好
         */
        private fun initMeiZiPrefConfig() {
            preference_meizi.summaryOn = getString(R.string.set_preference_meizi_summaryon)
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
            // 动画自动播放偏好点击
            preference_gifautoplay.setOnPreferenceChangeListener { preference, newValue ->
                preference_gifautoplay.summary = getGifAutoPlaySummary(newValue.toString())
                preference_gifautoplay.setValueIndex(newValue.toString().toInt())
                true
            }

            // 妹子开关偏好点击
            preference_meizi.setOnPreferenceChangeListener { _, newValue ->
                AppEvent.post(TemplateEvent(newValue as Boolean))
                true
            }
        }
    }

}