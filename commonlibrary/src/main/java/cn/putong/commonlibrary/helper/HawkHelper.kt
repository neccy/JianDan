package cn.putong.commonlibrary.helper

import com.orhanobut.hawk.Hawk

/**
 * Hawk(键值对库)工具类
 * Created by xinyi on 2018/1/15.
 */
object HawkHelper {

    private val KEY_TABSELECTION = "key_tab_selection"

    /**
     * 软件退出时保存当前Tab下标
     */
    fun saveTabSelection(position: Int) {
        Hawk.put(KEY_TABSELECTION, position)
    }

    /**
     * 获取上次退出时Tab下标
     */
    fun getTabSelection(): Int {
        return try {
            Hawk.get(KEY_TABSELECTION)
        } catch (e: Exception) {
            0
        }
    }
}