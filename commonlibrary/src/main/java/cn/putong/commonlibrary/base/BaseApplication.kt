package cn.putong.commonlibrary.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

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
        initSmartRefreshLsyout()
        initFresco()
    }

    override fun initArouter() {}

    override fun initSmartRefreshLsyout() {}

    override fun initFresco() {
//        val config = ImagePipelineConfig.newBuilder(this)
//                .setBitmapMemoryCacheParamsSupplier(bitmapCacheParamsSupplier)
//                .setCacheKeyFactory(cacheKeyFactory)
//                .setDownsampleEnabled(true)
//                .setWebpSupportEnabled(true)
//                .setEncodedMemoryCacheParamsSupplier(encodedCacheParamsSupplier)
//                .setExecutorSupplier(executorSupplier)
//                .setImageCacheStatsTracker(imageCacheStatsTracker)
//                .setMainDiskCacheConfig(mainDiskCacheConfig)
//                .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
//                .setNetworkFetchProducer(networkFetchProducer)
//                .setPoolFactory(poolFactory)
//                .setProgressiveJpegConfig(progressiveJpegConfig)
//                .setRequestListeners(requestListeners)
//                .setSmallImageDiskCacheConfig(smallImageDiskCacheConfig)
//                .build()
        Fresco.initialize(this)
    }
}