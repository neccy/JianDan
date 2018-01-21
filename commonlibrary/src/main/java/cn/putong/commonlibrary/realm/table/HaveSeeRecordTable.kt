package cn.putong.commonlibrary.realm.table

import io.realm.RealmObject

/**
 * 内容已看记录表
 * 存储已看过的内容的id
 * Created by xinyi on 2018/1/20.
 */
open class HaveSeeRecordTable(
        // 数据id
        var data_id: Int = 0
) : RealmObject()