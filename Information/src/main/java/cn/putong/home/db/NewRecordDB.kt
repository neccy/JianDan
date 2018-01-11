package cn.putong.home.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * 新闻类型数据已看记录表
 * Created by lala on 2018/1/12.
 */
class NewRecordDB(
        @PrimaryKey
        var id: Int = 0,
        // 数据id
        var new_date_id: Int = 0
) : RealmObject()