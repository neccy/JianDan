package cn.putong.commonlibrary.helper

import android.content.Context
import android.net.Uri
import android.util.DisplayMetrics
import android.view.WindowManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import me.relex.photodraweeview.PhotoDraweeView


/**
 * Fresco封装
 * Created by xinyi on 2018/1/9.
 */
object FrescoHelper {

    /**
     * 设置动画控制器
     * 主要用于加载GIF
     */
    fun setAnimatorController(uri: Uri, simpleDraweeView: SimpleDraweeView) {

        val request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build()

        simpleDraweeView.controller =
                Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .setImageRequest(request)
                        .setOldController(simpleDraweeView.controller)
                        .setUri(uri)
                        .build()
    }

    fun setAnimatorController(uri: Uri, photoDraweeView: PhotoDraweeView) {
        photoDraweeView.controller =
                Fresco.newDraweeControllerBuilder()
                        .setOldController(photoDraweeView.controller)
                        .setAutoPlayAnimations(true)
                        .setUri(uri)
                        .build()
    }

    private fun getScreenWidth(context: Context): Int {
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

}