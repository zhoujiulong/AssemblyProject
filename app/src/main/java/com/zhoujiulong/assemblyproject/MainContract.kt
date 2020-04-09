package com.zhoujiulong.assemblyproject

import com.zhoujiulong.baselib.base.BaseView
import com.zhoujiulong.baselib.http.listener.DownLoadListener

interface MainContract {

    interface View : BaseView {
        fun downLoadApkProgress(percent: Int)
        fun downLoadApkSuccess(fileAbsPath: String)
        fun downLoadApkFail(errorMsg: String)
    }

    interface Model {
        fun downLoadApk(downloadListener: DownLoadListener, filePath: String, fileName: String)
    }

    interface Presenter {
        fun downLoadApk()
    }

}