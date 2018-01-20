package cn.putong.commonlibrary.realm.table

import io.realm.RealmObject

/**
 * Post数据已看记录表
 * Created by xinyi on 2018/1/20.
 */
open class PostHaveSeeTable(
        // 数据id
        var post_id: Int = 0
) : RealmObject()