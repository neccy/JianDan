package cn.putong.home.helper

import com.orhanobut.hawk.Hawk

/**
 * Hawk(键值对库)工具类
 * Created by xinyi on 2018/1/15.
 */
object HawkHelper {

    // Tab下标 KEY
    private val KEY_TAB_SELECTION = "key_tab_selection"

    /**
     * 软件退出时保存当前Tab下标
     */
    fun saveTabSelection(position: Int) = Hawk.put(KEY_TAB_SELECTION, position)

    /**
     * 获取上次软件退出时保存的Tab下标
     */
    fun getTabSelection(): Int = try {
        Hawk.get(KEY_TAB_SELECTION)
    } catch (e: Exception) {
        0
    }
}