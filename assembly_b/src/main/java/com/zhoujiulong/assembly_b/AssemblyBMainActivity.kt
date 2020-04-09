package com.zhoujiulong.assembly_b

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhoujiulong.baselib.base.BaseActivity
import com.zhoujiulong.baselib.base.emptyimpl.EmptyPresenter
import com.zhoujiulong.commonlib.constants.ActivityRouteNameConstants
import kotlinx.android.synthetic.main.assemblyb_activity_assembly_b_main.*

/**
 * @author zhoujiulong
 * @createtime 2019/9/6 15:00
 */
@Route(path = ActivityRouteNameConstants.ASSEMBLY_B_MAIN, group = "assembly_b")
class AssemblyBMainActivity : BaseActivity<EmptyPresenter>() {

    override fun getLayoutId(): Int = R.layout.assemblyb_activity_assembly_b_main

    override fun initPresenter() {
    }

    override fun initView() {
    }

    override fun initListener() {
        setOnClick(tv_go_a)
    }

    override fun initData() {
    }

    override fun getData() {
    }

    override fun onClick(v: View?) {
        when (v) {
            tv_go_a -> ARouter.getInstance().build(ActivityRouteNameConstants.ASSEMBLY_A_MAIN).navigation()
        }
    }

}
