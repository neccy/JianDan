package cn.putong.commonlibrary.realm.information.table

import io.realm.RealmObject

/**
 * 新闻类型缓存数据表
 * Created by xinyi on 2018/1/13.
 */
open class CacheTable(
        // 缓存数据类型(1:新鲜事 2:无聊图 3:段子)
        var type: Int = 0,
        // 缓存数据
        var cache_data: String = ""
) : RealmObject()