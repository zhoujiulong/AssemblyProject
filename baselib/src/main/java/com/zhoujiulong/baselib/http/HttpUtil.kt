package com.zhoujiulong.baselib.http


import com.zhoujiulong.baselib.http.listener.DownLoadListener
import com.zhoujiulong.baselib.http.listener.OnTokenInvalidListener
import com.zhoujiulong.baselib.http.listener.RequestListener
import com.zhoujiulong.baselib.http.other.TimeOut
import okhttp3.Interceptor
import okhttp3.ResponseBody

/**
 * Author : zhoujiulong
 * Email : 754667445@qq.com
 * Time : 2017/03/24
 * Desc : 网络请求工具类
 */
object HttpUtil {

    /**
     * 获取BaseUrl
     */
    val mBaseUrl: String
        get() = ServiceManager.instance.mBaseUrl

    /**
     * 设置 Token 失效回调，全局只调用一次，建议在 Application 中设置
     */
    fun initTokenInvalidListener(onTokenInvalidListener: OnTokenInvalidListener) {
        RequestHelper.instance.setOnTokenInvalidListener(onTokenInvalidListener)
    }

    /**
     * 添加请求头部拦截器
     */
    fun addInterceptor(interceptor: Interceptor) {
        ServiceManager.instance.addInterceptor(interceptor)
    }

    /**
     * 获取 Call ，使用传入的 BaseUrl
     *
     * @param callClass 需要获取的 Call 对应的 Class
     * @param baseUrl   BaseUrl
     * @param tempInterceptor 临时的拦截器
     * @param timeOuts  TimeOut 超时设置，可变参数，不设置使用默认的
     * @param <T>       返回的 Call 类型
    </T> */
    @Synchronized
    fun <T> getService(
        callClass: Class<T>, baseUrl: String = mBaseUrl,
        tempInterceptor: List<Interceptor>? = null, vararg timeOuts: TimeOut
    ): T {
        return ServiceManager.instance.getService(
            callClass, baseUrl, tempInterceptor, timeOuts = *timeOuts
        )
    }

    /**
     * 发送请求
     *
     * @param reTag      请求标记，用于取消请求用
     * @param listener 请求完成后的回调
     * @param <T>      请求返回的数据对应的类型，第一层必须继承 BaseResponse
    </T> */
    fun <T> sendRequest(reTag: String, call: retrofit2.Call<T>, listener: RequestListener<T>) {
        RequestHelper.instance.sendRequest(reTag, call, listener)
    }

    /**
     * 发送下载网络请求
     *
     * @param reTag              请求标记，用于取消请求用
     * @param downLoadFilePath 下载文件保存路径
     * @param downloadListener 下载回调
     */
    fun sendDownloadRequest(
        reTag: String, call: retrofit2.Call<ResponseBody>, downLoadFilePath: String,
        fileName: String, downloadListener: DownLoadListener
    ) {
        RequestHelper.instance
            .sendDownloadRequest(reTag, call, downLoadFilePath, fileName, downloadListener)
    }

    /**
     * 根据请求的标记 reTag 取消请求和 Observer
     */
    fun cancelWithTag(reTag: String) {
        RequestManager.instance.cancelRequestWithTag(reTag)
    }
}




















