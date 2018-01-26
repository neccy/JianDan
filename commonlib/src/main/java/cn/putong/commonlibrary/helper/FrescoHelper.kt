package cn.putong.commonlibrary.helper

import android.graphics.drawable.Animatable
import android.net.Uri
import cn.putong.commonlibrary.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo
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

    fun setControllerListener(simpleDraweeView: SimpleDraweeView,
                              imagePath: String, imageWidth: Int) {
        val layoutParams = simpleDraweeView.layoutParams
        val controllerListener = object : BaseControllerListener<ImageInfo>() {
            override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?,
                                         anim: Animatable?) {
                if (imageInfo == null) {
                    return
                }

                val height = imageInfo.height
                val width = imageInfo.width
                layoutParams.width = imageWidth
                layoutParams.height = ((imageWidth * height).toFloat() / width.toFloat()).toInt()
                simpleDraweeView.layoutParams = layoutParams
            }

            override fun onIntermediateImageSet(id: String?, imageInfo: ImageInfo?) {

            }

            override fun onFailure(id: String?, throwable: Throwable?) {
                throwable!!.printStackTrace()
            }
        }
        val request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(imagePath))
                .setProgressiveRenderingEnabled(true)
                .build()

        simpleDraweeView.controller =
                Fresco.newDraweeControllerBuilder()
                        .setControllerListener(controllerListener)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .setImageRequest(request)
                        .setOldController(simpleDraweeView.controller)
                        .build()
    }

}