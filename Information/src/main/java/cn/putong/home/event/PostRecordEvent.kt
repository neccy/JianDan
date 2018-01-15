package cn.putong.home.event

/**
 * 用于存储已看记录后通知新闻列表更新适配器
 * Created by xinyi on 2018/1/12.
 */
class PostRecordEvent(
        // 当前新闻数据下标
        val position: Int
)