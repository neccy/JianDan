package cn.putong.commonlibrary.base

import android.app.Application
import cn.putong.commonlibrary.R
import cn.putong.commonlibrary.realm.information.module.InformationModule
import com.facebook.drawee.backends.pipeline.Fresco
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Base Application
 * Created by xinyi on 2018/1/6.
 */
open class BaseApplication : Application(), IBaseThreadLibsImpl {

    override fun onCreate() {
        super.onCreate()
        initThreadLibs()
    }

    private fun initThreadLibs() {
        initArouter()
        initFresco()
        initRealm()
    }

    override fun initArouter() {}

    override fun initFresco() {
        Fresco.initialize(this)
    }

    override fun initRealm() {
        super.initRealm()
        val filename = getString(R.string.app_db_name)
        val schemaVersion: Long = 1
        Realm.init(this)
        val realmConfig = RealmConfiguration
                .Builder()
                .name(filename)
                .modules(InformationModule())
                .schemaVersion(schemaVersion)
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}