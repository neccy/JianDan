package cn.putong.commonlibrary.util

import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*


/**
 * 时间工具类
 * Created by xinyi on 2018/1/8.
 */
object TimeUtil {

    private val DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

    private val prettyTime = PrettyTime()

    /**
     * 将日期转换成[几小时前 几小时后]格式
     * @param date 日期
     */
    fun format(date: Date): String {
        return prettyTime.format(date).
                replace("前", "前").
                replace("后", "后")
    }

    /**
     *获取Date
     */
    fun getDate(time: String, pattern: String = DEFAULT_TIME_PATTERN): Date {
        val sdf = SimpleDateFormat(pattern)
        return sdf.parse(time)
    }
}