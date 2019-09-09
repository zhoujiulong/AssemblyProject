package com.zhoujiulong.assembly_b

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhoujiulong.commonlib.ActivityRouteNameConstants
import kotlinx.android.synthetic.main.assemblyb_activity_assembly_b_main.*

/**
 * @author zhoujiulong
 * @createtime 2019/9/6 15:00
 */
@Route(path = ActivityRouteNameConstants.ASSEMBLY_B_MAIN, group = "assembly_b")
class AssemblyBMainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.assemblyb_activity_assembly_b_main)

        tv_go_a.setOnClickListener {
            ARouter.getInstance().build(ActivityRouteNameConstants.ASSEMBLY_A_MAIN).navigation()
        }
    }
}
