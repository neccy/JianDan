package cn.putong.commonlibrary.realm.information.table

import io.realm.RealmObject

/**
 * 新闻类型数据已看记录表
 * Created by lala on 2018/1/12.
 */
open class PostRecordTable(
        // 数据id
        var new_data_id: Int = 0
) : RealmObject()