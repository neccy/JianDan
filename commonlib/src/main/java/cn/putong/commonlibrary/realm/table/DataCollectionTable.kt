package cn.putong.commonlibrary.realm.table

import io.realm.RealmObject

/**
 * 数据收藏表
 * Created by xinyi on 2018/1/22.
 */
open class DataCollectionTable(
        // 所属模版
        var template: Int = 0,
        // 数据
        var data: String = ""
) : RealmObject()