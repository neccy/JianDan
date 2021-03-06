package cn.putong.commonlibrary.base

import android.os.Bundle
import android.preference.PreferenceFragment

/**
 * Base PreederenceFragment
 * Created by xinyi on 2018/1/17.
 */
open class BasePreferenceFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initResource()
        initPreference()
        initConfig()
        initListener()
    }

    open fun initResource() {}

    open fun initPreference() {}

    open fun initConfig() {}

    open fun initListener() {}
}