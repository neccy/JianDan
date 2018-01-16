package cn.putong.detail.helper

import android.webkit.WebView

/**
 * HTML工具类
 * Created by xinyi on 2018/1/11.
 */
object HtmlHelper {

    var CONTENT = ""
    private val BASEURL = ""
    private val MIMETYPE = "text/html"
    private val ENCODING = "utf-8"
    private val HISTORYURL = ""
    private val CSS = "new_detail_style.css"

    /**
     * 根据新鲜事接口返回内容信息生成HTML
     */
    private fun getHtml(): String {
        val sb = StringBuilder()
        sb.append("<!DOCTYPE html>")
        sb.append("<html dir=\"ltr\" lang=\"zh\">")
        sb.append("<head>")
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />")
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/${CSS}' type=\"text/css\" media=\"screen\" />")
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
        sb.append(CONTENT)
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

    /**
     * 设置详情Url
     */
    fun setUrl(webView: WebView) {
        webView.loadDataWithBaseURL(
                BASEURL,
                getHtml(),
                MIMETYPE,
                ENCODING,
                HISTORYURL)
    }
}