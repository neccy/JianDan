package cn.putong.commonlibrary.realm

import io.realm.Realm

/**
 * 应用数据库
 * Created by lala on 2018/1/12.
 */
object AppDB {

    fun getInstance(): Realm = Realm.getDefaultInstance()

}