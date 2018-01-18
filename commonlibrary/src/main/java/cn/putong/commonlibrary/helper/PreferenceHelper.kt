package cn.putong.commonlibrary.helper

import android.content.Context
import cn.putong.commonlibrary.R
import cn.putong.commonlibrary.base.BaseFragment
import org.jetbrains.anko.support.v4.defaultSharedPreferences

/**
 * 获取妹子开关Value
 */
fun BaseFragment.getMeiZiValue(context: Context): Boolean {
    val defValue = false
    return defaultSharedPreferences.getBoolean(
            context.getString(R.string.key_preference_meizi), defValue)
}

/**
 * 获取不受欢迎开关Value
 */
fun BaseFragment.getUnWelcomeValue(context: Context): Boolean {
    val defValue = false
    return defaultSharedPreferences.getBoolean(
            context.getString(R.string.key_preference_unwelcome), defValue)
}