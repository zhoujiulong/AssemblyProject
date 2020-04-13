package com.zhoujiulong.assemblyproject

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.zhoujiulong.baselib.base.BaseActivity
import com.zhoujiulong.commonlib.constants.RouteNameConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initPresenter() {
        mPresenter = MainPresenter()
    }

    override fun initView() {
    }

    override fun initListener() {
        setOnClick(tv_go_a, tv_go_b, tv_download_file)
    }

    override fun initData() {
    }

    override fun getData() {
    }

    override fun onClick(v: View?) {
        when (v) {
            tv_go_a -> ARouter.getInstance().build(RouteNameConstants.ASSEMBLY_A_MAIN).navigation()
            tv_go_b -> ARouter.getInstance().build(RouteNameConstants.ASSEMBLY_B_MAIN).navigation()
            tv_download_file -> mPresenter?.downLoadApk()
        }
    }

    override fun downLoadApkProgress(percent: Int) {
        tv_download_detail.text = "下载进度：${percent}"
    }

    override fun downLoadApkSuccess(fileAbsPath: String) {
        tv_download_detail.text = "下载文件成功:${fileAbsPath}"
    }

    override fun downLoadApkFail(errorMsg: String) {
        tv_download_detail.text = "下载文件失败：${errorMsg}"
    }
}
