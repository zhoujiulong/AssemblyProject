package com.zhoujiulong.baselib.utils

/**
 * @author zhoujiulong
 * @createtime 2019/4/11 9:32
 */
object FileUtil {

    /**
     * @return 从下载连接中解析出文件名
     */
    fun getNameFromUrl(url: String): String {
        return url.substring(url.lastIndexOf("/") + 1)
    }

}
















