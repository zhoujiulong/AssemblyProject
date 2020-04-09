package com.zhoujiulong.assemblyproject

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.zhoujiulong.baselib.base.BaseActivity
import com.zhoujiulong.baselib.base.emptyimpl.EmptyPresenter
import com.zhoujiulong.commonlib.constants.ActivityRouteNameConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<EmptyPresenter>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initPresenter() {
    }

    override fun initView() {
    }

    override fun initListener() {
        setOnClick(tv_go_a, tv_go_b)
    }

    override fun initData() {
    }

    override fun getData() {
    }

    override fun onClick(v: View?) {
        when (v) {
            tv_go_a -> ARouter.getInstance().build(ActivityRouteNameConstants.ASSEMBLY_A_MAIN).navigation()
            tv_go_b -> ARouter.getInstance().build(ActivityRouteNameConstants.ASSEMBLY_B_MAIN).navigation()
        }
    }

}
