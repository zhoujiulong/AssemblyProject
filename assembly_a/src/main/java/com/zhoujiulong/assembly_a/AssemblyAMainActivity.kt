package com.zhoujiulong.assembly_a

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhoujiulong.commonlib.ActivityRouteNameConstants
import kotlinx.android.synthetic.main.assemblya_activity_assembly_a_main.*

/**
 * @author zhoujiulong
 * @createtime 2019/9/6 14:58
 */
@Route(path = ActivityRouteNameConstants.ASSEMBLY_A_MAIN, group = "assembly_a")
class AssemblyAMainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.assemblya_activity_assembly_a_main)

        tv_go_b.setOnClickListener {
            ARouter.getInstance().build(ActivityRouteNameConstants.ASSEMBLY_B_MAIN).navigation()
        }
    }
}
