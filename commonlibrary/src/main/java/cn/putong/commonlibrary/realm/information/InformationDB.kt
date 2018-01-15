package cn.putong.commonlibrary.realm.information

import cn.putong.commonlibrary.realm.AppDB
import cn.putong.commonlibrary.realm.information.table.CacheTable
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

    /**
     * 保存缓存数据
     */
    fun saveCacheData(cache_data: String, type: Int) {
        // 先删除数据库中当前分类对应的数据再添加,缓存数据只保存当前最新的第一页数据
        val fieldName = "type"
        val mCacheDatas = AppDB
                .getInstance()
                .where(CacheTable::class.java)
                .equalTo(fieldName, type)
                .findAll()

        AppDB.getInstance().executeTransaction { realm ->
            if (mCacheDatas.isNotEmpty())
                mCacheDatas.deleteAllFromRealm()

            val mCacheTb = realm.createObject(CacheTable::class.java)
            mCacheTb.cache_data = cache_data
            mCacheTb.type = type
        }
    }

}