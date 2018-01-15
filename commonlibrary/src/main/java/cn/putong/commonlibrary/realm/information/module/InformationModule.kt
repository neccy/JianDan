package cn.putong.commonlibrary.realm.information.module

import cn.putong.commonlibrary.realm.information.table.CacheTable
import cn.putong.commonlibrary.realm.information.table.PostRecordTable
import io.realm.annotations.RealmModule

/**
 * 包含Realm的库必须通过RealmModule公开和使用他们的模式
 * RealmModule为库项目生成默认值，这会与RealmModule应用程序使用的默认值相冲突
 * Created by xinyi on 2018/1/12.
 */
@RealmModule(library = true, classes = [(PostRecordTable::class), (CacheTable::class)])
open class InformationModule