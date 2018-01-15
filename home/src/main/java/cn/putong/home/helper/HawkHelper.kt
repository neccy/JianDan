package cn.putong.home.helper

import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel
import com.orhanobut.hawk.Hawk

/**
 * Hawk(键值对库)工具类
 * Created by xinyi on 2018/1/15.
 */
object HawkHelper {

    // Tab下标 KEY
    private val KEY_TAB_SELECTION = "key_tab_selection"

    // 新鲜事数据缓存 KEY
    private val KEY_NEW_DATA_CACHE = "key_new_data_cache"
    // 无聊图数据缓存 KEY
    private val KEY_PIC_DATA_CACHE = "key_pic_data_cache"
    // 段子数据缓存 KEY
    private val KEY_DUANZI_DATA_CACHE = "key_duanzi_data_cache"

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

    /**
     * 保存新鲜事缓存
     */
    fun saveNewCache(mNews: List<PostModel.Post>) {
        Hawk.put(KEY_NEW_DATA_CACHE, mNews)
    }

    /**
     * 获取新鲜事缓存数据
     */
    fun getNewCache(): ArrayList<PostModel.Post> {
        var cacheData = ArrayList<PostModel.Post>()
        if (Hawk.contains(KEY_NEW_DATA_CACHE)) {
            cacheData = Hawk.get(KEY_NEW_DATA_CACHE)
            if (cacheData == null)
                cacheData = arrayListOf()
        }
        return cacheData
    }

    /**
     * 保存无聊图缓存
     */
    fun savePicCache(mNews: List<CommentModel.Comment>) {
        Hawk.put(KEY_PIC_DATA_CACHE, mNews)
    }

    /**
     * 获取无聊图缓存数据
     */
    fun getPicCache(): ArrayList<CommentModel.Comment> {
        var cacheData = ArrayList<CommentModel.Comment>()
        if (Hawk.contains(KEY_PIC_DATA_CACHE)) {
            cacheData = Hawk.get(KEY_PIC_DATA_CACHE)
            if (cacheData == null)
                cacheData = arrayListOf()
        }
        return cacheData
    }

    /**
     * 保存段子缓存
     */
    fun saveDuanZiCache(mNews: List<CommentModel.Comment>) {
        Hawk.put(KEY_DUANZI_DATA_CACHE, mNews)
    }

    /**
     * 获取段子缓存数据
     */
    fun getDuanZiCache(): ArrayList<CommentModel.Comment> {
        var cacheData = ArrayList<CommentModel.Comment>()
        if (Hawk.contains(KEY_DUANZI_DATA_CACHE)) {
            cacheData = Hawk.get(KEY_DUANZI_DATA_CACHE)
            if (cacheData == null)
                cacheData = arrayListOf()
        }
        return cacheData
    }

}