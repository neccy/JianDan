package cn.putong.commonlibrary.realm

import cn.putong.commonlibrary.realm.table.DataCollectionTable
import cn.putong.commonlibrary.realm.table.HaveSeeRecordTable
import cn.putong.commonlibrary.realm.table.NegativeRecordTable
import cn.putong.commonlibrary.realm.table.PositiveRecordTable
import io.realm.Realm
import io.realm.RealmResults

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
    fun saveHaveSeeRecord(id: Int, onResultListener: (Boolean) -> Unit) {
        if (getHaveSeeRecord(id) == null)
            getInstance().executeTransaction { realm ->
                try {
                    val mHaveSeeRecordTb = realm
                            .createObject(HaveSeeRecordTable::class.java)
                    mHaveSeeRecordTb.data_id = id
                    onResultListener.invoke(true)
                } catch (e: Exception) {
                    onResultListener.invoke(false)
                }
            }
    }

    /**
     * 根据id获取已看记录
     */
    fun getHaveSeeRecord(id: Int): HaveSeeRecordTable? {
        val fieldName = "data_id"
        return getInstance()
                .where(HaveSeeRecordTable::class.java)
                .equalTo(fieldName, id)
                .findFirst()
    }

    /**
     * 根据id保存点赞记录
     */
    fun savePositiveRecord(id: String) {
        getInstance().executeTransaction { realm ->
            val mPositiveRecoed = realm
                    .createObject(PositiveRecordTable::class.java)
            mPositiveRecoed.comment_id = id.toInt()
        }
    }

    /**
     * 根据id获取已赞记录
     */
    fun getPositiveRecord(id: String): PositiveRecordTable? {
        val fieldName = "comment_id"
        return getInstance()
                .where(PositiveRecordTable::class.java)
                .equalTo(fieldName, id.toInt())
                .findFirst()
    }

    /**
     * 根据id保存讨厌记录
     */
    fun saveNegativeRecord(id: String) {
        getInstance().executeTransaction { realm ->
            val mNegativeRecord = realm
                    .createObject(NegativeRecordTable::class.java)
            mNegativeRecord.comment_id = id.toInt()
        }
    }

    /**
     * 根据id获取讨厌记录
     */
    fun getNegativeRecord(id: String): NegativeRecordTable? {
        val fieldName = "comment_id"
        return getInstance()
                .where(NegativeRecordTable::class.java)
                .equalTo(fieldName, id.toInt())
                .findFirst()
    }

    /**
     * 根据所属模版和数据将数据保存到收藏
     */
    fun saveDataCollection(template: Int, data: String) {
        getInstance().executeTransaction { realm ->
            val mDataController = realm.createObject(DataCollectionTable::class.java)
            mDataController.template = template
            mDataController.data = data
        }
    }

    /**
     * 根据模版获取收藏的数据列表
     */
    fun getDataCollection(template: Int): RealmResults<DataCollectionTable>? {
        val fieldName = "template"
        return getInstance()
                .where(DataCollectionTable::class.java)
                .equalTo(fieldName, template)
                .findAll()
    }

}