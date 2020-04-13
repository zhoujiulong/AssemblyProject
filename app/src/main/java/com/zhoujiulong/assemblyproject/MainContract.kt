package com.zhoujiulong.assemblyproject

import com.zhoujiulong.baselib.base.BaseModel
import com.zhoujiulong.baselib.base.BasePresenter
import com.zhoujiulong.baselib.base.BaseView
import com.zhoujiulong.baselib.http.listener.DownLoadListener

interface MainContract {

    interface View : BaseView {
        fun downLoadApkProgress(percent: Int)
        fun downLoadApkSuccess(fileAbsPath: String)
        fun downLoadApkFail(errorMsg: String)
    }

    abstract class Model : BaseModel<MainServices>() {
        abstract fun downLoadApk(
            downloadListener: DownLoadListener,
            filePath: String,
            fileName: String
        )
    }

    abstract class Presenter : BasePresenter<Model, View>() {
        abstract fun downLoadApk()
    }

}