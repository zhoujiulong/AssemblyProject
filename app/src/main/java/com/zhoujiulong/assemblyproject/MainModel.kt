package com.zhoujiulong.assemblyproject

import com.zhoujiulong.baselib.http.HttpUtil
import com.zhoujiulong.baselib.http.listener.DownLoadListener

class MainModel : MainContract.Model() {

    override fun initService() = HttpUtil.getService(MainServices::class.java)

    override fun downLoadApk(
        downloadListener: DownLoadListener, filePath: String, fileName: String
    ) {
        HttpUtil.sendDownloadRequest(
            ReTag,
            mService.downLoadApk("http://imtt.dd.qq.com/16891/9FFDE35ADEFC28D3740D4E16612F078A.apk?fsname=com.tencent.tmgp.sgame_1.22.1.13_22011304.apk&csr=1bbd"),
            filePath, fileName, downloadListener
        )
    }

}