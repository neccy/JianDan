package cn.putong.commonlibrary.realm.table

import io.realm.RealmObject

/**
 * Comment数据操作记录表(点赞、讨厌)
 * Created by xinyi on 2018/1/20.
 */
open class CommentOperatTable(
        // 点赞数据id
        var positive_comment_id: Int? = 0,
        var negative_comment_id: Int? = 0
) : RealmObject()