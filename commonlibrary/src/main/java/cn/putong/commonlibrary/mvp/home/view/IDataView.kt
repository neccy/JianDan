package cn.putong.commonlibrary.mvp.home.view

import cn.putong.commonlibrary.base.IBaseViewImpl
import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.mvp.home.model.PostModel

/**
 *
 * Created by xinyi on 2018/1/8.
 */
interface IDataView : IBaseViewImpl {

    /**
     * 获取当前页数
     */
    fun getCurrentPage(): Int

    /**
     * 更新Post数据
     */
    fun updatePostData(postModel: PostModel)

    /**
     * 更新Comment数据
     */
    fun updateCommentData(commentModel: CommentModel)

}