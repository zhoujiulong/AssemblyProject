package com.zhoujiulong.baselib.base;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zhoujiulong.baselib.BuildConfig;
import com.zhoujiulong.baselib.utils.ContextUtil;

/**
 * @author zhoujiulong
 * @createtime 2019/7/11 13:34
 */
public class SimpleApplication extends Application {

    private static Application mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ContextUtil.init(this);
        initLogger();
        initARouter();
    }

    /**
     * 初始化日志输入框架
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true).methodCount(1).methodOffset(1).tag("xingfugolog").build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG_MODE;
            }
        });
    }

    /**
     * 初始化 ARouter
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    public static Application getInstance() {
        return mInstance;
    }

}

















