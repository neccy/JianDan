package cn.putong.home.util

/**
 * HTML工具类
 * Created by xinyi on 2018/1/11.
 */
object HtmlUtil {

    val BASE_URL = ""
    val MIME_TYPE = "text/html"
    val ENCODING = "utf-8"
    val HISTORY_URL = ""

    /**
     * 根据新鲜事接口返回内容信息生成HTML
     */
    fun getHtml(content: String): String {
        val sb = StringBuilder()
        sb.append("<!DOCTYPE html>")
        sb.append("<html dir=\"ltr\" lang=\"zh\">")
        sb.append("<head>")
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />")
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/new_detail_style.css' type=\"text/css\" media=\"screen\" />")
        sb.append("</head>")
        sb.append("<body style=\"padding:0px 8px 8px 8px;\">")
        sb.append("<div id=\"pagewrapper\">")
        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">")
        sb.append("<div id=\"maincontent\">")
        sb.append("<div class=\"post\">")
        sb.append("<div class=\"posthit\">")
        sb.append("<div class=\"postinfo\">")
        sb.append("</div>")
        sb.append("<div class=\"entry\">")
        sb.append(content)
        sb.append("</div>")
        sb.append("</div>")
        sb.append("</div>")
        sb.append("</div>")
        sb.append("</div>")
        sb.append("</div>")
        sb.append("</body>")
        sb.append("</html>")
        return sb.toString()
    }
}