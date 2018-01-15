package cn.putong.commonlibrary.realm.information

import cn.putong.commonlibrary.realm.AppDB
import cn.putong.commonlibrary.realm.information.table.PostRecordTable

/**
 * 资讯(information)模块数据库封装
 * Created by xinyi on 2018/1/12.
 */
object InformationDB {

    /**
     * 根据id保存已看记录
     * @param onResultListener 回调接口
     */
    fun savePostRecord(id: Int, onResultListener: (Boolean) -> Unit) {
        if (getPostRecord(id) == null)
            AppDB.getInstance().executeTransaction { realm ->
                try {
                    val mNewRecordTb = realm.createObject(PostRecordTable::class.java)
                    mNewRecordTb.new_data_id = id
                    onResultListener.invoke(true)
                } catch (e: Exception) {
                    onResultListener.invoke(false)
                }
            }
    }

    /**
     * 根据id获取已看记录
     */
    fun getPostRecord(id: Int): PostRecordTable? {
        val fieldName = "new_data_id"
        return AppDB.getInstance().where(PostRecordTable::class.java).equalTo(fieldName, id).findFirst()
    }

}