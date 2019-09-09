package com.zhoujiulong.assemblyproject

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.zhoujiulong.commonlib.ActivityRouteNameConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_go_a.setOnClickListener {
            ARouter.getInstance().build(ActivityRouteNameConstants.ASSEMBLY_A_MAIN).navigation()
        }
        tv_go_b.setOnClickListener {
            ARouter.getInstance().build(ActivityRouteNameConstants.ASSEMBLY_B_MAIN).navigation()
        }
    }
}
