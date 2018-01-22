package cn.putong.commonlibrary.realm.table

import io.realm.RealmObject

/**
 * 点赞记录表
 * Created by xinyi on 2018/1/20.
 */
open class PositiveRecordTable(
        // 点赞数据id
        var comment_id: Int = 0
) : RealmObject()