package com.zhoujiulong.assemblyproject

import com.zhoujiulong.baselib.http.listener.DownLoadListener
import com.zhoujiulong.baselib.utils.ContextUtil

class MainPresenter : MainContract.Presenter() {

    override fun initModel() = MainModel()

    override fun downLoadApk() {
        val fileDir = ContextUtil.getContext().cacheDir.absolutePath
        val fileName = "downLoad.apk"
        mView?.showLoading()
        mModel?.downLoadApk(object : DownLoadListener() {
            override fun onProgress(progress: Int) {
                mView?.downLoadApkProgress(progress)
            }

            override fun onDone(fileAbsPath: String) {
                mView?.hideLoading()
                mView?.downLoadApkSuccess(fileAbsPath)
            }

            override fun onFail(errorInfo: String) {
                mView?.hideLoading()
                mView?.downLoadApkFail(errorInfo)
            }
        }, fileDir, fileName)
    }

}