package com.zhoujiulong.assemblyproject

import com.zhoujiulong.baselib.http.HttpUtil
import com.zhoujiulong.baselib.http.listener.DownLoadListener

class MainModel : MainContract.Model() {

    override fun initService() = HttpUtil.getService(MainServices::class.java)

    override fun downLoadApk(
        downloadListener: DownLoadListener,
        filePath: String,
        fileName: String
    ) {
        HttpUtil.sendDownloadRequest(
            ReTag,
            mService.downLoadApk(),
            filePath,
            fileName,
            downloadListener
        )
    }

}