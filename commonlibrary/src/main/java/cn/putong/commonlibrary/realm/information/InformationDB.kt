package cn.putong.commonlibrary.realm.information

import cn.putong.commonlibrary.realm.AppDB
import cn.putong.commonlibrary.realm.information.table.PostRecordTable

/**
 * 资讯(information)模块数据库封装
 * Created by xinyi on 2018/1/12.
 */
object InformationDB {

    fun saveNewRecord(id: Int) {
        AppDB.getInstance().executeTransaction { realm ->
            val mNewRecordTb = realm.createObject(PostRecordTable::class.java)
            mNewRecordTb.new_data_id = id
        }
    }
}