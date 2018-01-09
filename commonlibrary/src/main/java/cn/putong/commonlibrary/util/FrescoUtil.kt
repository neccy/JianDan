package cn.putong.commonlibrary.util

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController

/**
 * Fresco封装
 * Created by xinyi on 2018/1/9.
 */
object FrescoUtil {

    fun getController(url: String) : DraweeController {
        return Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setTapToRetryEnabled(true)
                .setAutoPlayAnimations(true)
                .build()
    }
}