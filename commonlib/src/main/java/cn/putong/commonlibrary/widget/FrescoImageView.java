package cn.putong.commonlibrary.widget;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import javax.annotation.Nullable;

import cn.putong.commonlibrary.R;

/**
 * SimpleDrawer封装
 *
 * @author xinyi
 * @date 2018/1/26
 */

public class FrescoImageView extends SimpleDraweeView {

    /**
     * 下载监听器
     */
    private ControllerListener mControllerListener;

    /**
     * 加载图片的Tag,用于不重复加载图片
     */
    private String uriPathTag;


    public FrescoImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init(context);
    }

    public FrescoImageView(Context context) {
        super(context);
    }

    public FrescoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FrescoImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public FrescoImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mControllerListener = new DefaultBaseControllerListener();
    }

    private class DefaultBaseControllerListener extends BaseControllerListener<ImageInfo> {
        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);
            FrescoImageView.this.setTag(R.id.fresco_tag, uriPathTag);
        }
    }

    public FrescoImageView setControllerListener(ControllerListener mControllerListener) {
        this.mControllerListener = mControllerListener;
        return this;
    }

    private ControllerListener getControllerListener() {
        return mControllerListener;
    }

    private FrescoImageView setController(final Uri uri) {
        uriPathTag = uri.toString();
        if (noRepeatLoadImage(uriPathTag)) {
            final AbstractDraweeController controller =
                    Fresco.newDraweeControllerBuilder()
                            .setAutoPlayAnimations(true)
                            .setControllerListener(this.getControllerListener())
                            .setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri)
                                    .setProgressiveRenderingEnabled(true)
                                    .build())
                            .setOldController(this.getController())
                            .build();
            super.setController(controller);
        }
        return this;
    }

    /**
     * 加载uri图片
     */
    public FrescoImageView setDraweeViewUri(final Uri uri) {
        return setController(uri);
    }

    /**
     * 判定Tag和Url是否相等，相等代表图片已经加载过，不需要从新加载
     */
    private boolean noRepeatLoadImage(String imgUrl) {
        return !(TextUtils.isEmpty(imgUrl)
                || TextUtils.isEmpty(this.getTag(R.id.fresco_tag) + ""))
                && !(this.getTag(R.id.fresco_tag) + "").equals(imgUrl);
    }


}
