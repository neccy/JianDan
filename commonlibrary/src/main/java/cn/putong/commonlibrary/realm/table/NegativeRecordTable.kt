package cn.putong.commonlibrary.realm.table

import io.realm.RealmObject

/**
 * 讨厌记录表
 * Created by lala on 2018/1/21.
 */
open class NegativeRecordTable(
        // 讨厌数据id
        var comment_id: Int = 0
) :
        RealmObject()