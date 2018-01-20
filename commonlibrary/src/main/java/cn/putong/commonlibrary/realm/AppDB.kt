package cn.putong.commonlibrary.realm

import cn.putong.commonlibrary.realm.table.PostHaveSeeTable
import io.realm.Realm

/**
 * 应用数据库
 * Created by lala on 2018/1/12.
 */
object AppDB {

    private fun getInstance(): Realm = Realm.getDefaultInstance()

    /**
     * 根据id保存已看记录
     * @param onResultListener 回调接口
     */
    fun savePostRecord(id: Int, onResultListener: (Boolean) -> Unit) {
        if (getPostRecord(id) == null)
            getInstance().executeTransaction { realm ->
                try {
                    val mNewRecordTb = realm.createObject(PostHaveSeeTable::class.java)
                    mNewRecordTb.post_id = id
                    onResultListener.invoke(true)
                } catch (e: Exception) {
                    onResultListener.invoke(false)
                }
            }
    }

    /**
     * 根据id获取已看记录
     */
    fun getPostRecord(id: Int): PostHaveSeeTable? {
        val fieldName = "post_id"
        return getInstance().where(PostHaveSeeTable::class.java).equalTo(fieldName, id).findFirst()
    }
}