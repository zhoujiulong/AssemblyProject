package com.zhoujiulong.baselib.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.zhoujiulong.baselib.app.ActivityFragmentManager

/**
 * Author : zhoujiulong
 * Email : 754667445@qq.com
 * Time : 2017/03/24
 * Desc : SimpleFragment
 */
abstract class SimpleFragment : Fragment(), View.OnClickListener {

    /**
     * 網絡請求標記tag
     */
    val ReTag = System.currentTimeMillis().toString()
    private var mISFirstResume = true

    protected var mContext: Context? = null
    protected var mActivity: Activity? = null
    protected lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)
        ActivityFragmentManager.getInstance().addFragment(this)

        mContext = activity
        mActivity = activity
        return mRootView
    }

    override fun onResume() {
        super.onResume()
        if (mISFirstResume) {
            mISFirstResume = false

            initPresenter()
            attachView()
            initView()
            initListener()
            initData()
            getData()

            //懒加载，有可能先执行此处再执行 setUserVisibleHint 方法
            mIsPrepared = true
            if (userVisibleHint && mIsFirstTimeLoadData) {
                mIsFirstTimeLoadData = false
                getDataLazy()
            }
        }
    }

    override fun onDestroyView() {
        ActivityFragmentManager.getInstance().removeFragment(this)
        detachView()
        mContext = null
        mActivity = null
        super.onDestroyView()
    }

    /* ********************************************** 初始化相关方法 **************************************************** */
    /* ********************************************** 初始化相关方法 **************************************************** */

    /**
     * 获取布局资源 id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化逻辑处理层
     */
    protected abstract fun initPresenter()

    protected abstract fun attachView()

    /**
     * 初始化控件
     */
    protected abstract fun initView()

    /**
     * 初始化监听事件
     */
    protected abstract fun initListener()

    /**
     * 初始化数据,设置数据
     */
    protected abstract fun initData()

    /**
     * 获取网络数据
     */
    protected abstract fun getData()

    /**
     * 懒加载数据在 ViewPager 管理的 Fragment 中才能使用
     */
    protected fun getDataLazy() {}

    protected abstract fun detachView()

    /**
     * 設置點擊
     */
    fun setOnClick(@IdRes vararg viewIds: Int) {
        for (id in viewIds) {
            findViewById<View>(id).setOnClickListener(this)
        }
    }

    /**
     * 設置點擊
     */
    fun setOnClick(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    fun <T : View> findViewById(@IdRes viewId: Int): T {
        return mRootView.findViewById(viewId)
    }

    /* ********************************************** 懒加载数据在 ViewPager 管理的 Fragment 中才能使用 **************************************************** */
    /* ********************************************** 懒加载数据在 ViewPager 管理的 Fragment 中才能使用 **************************************************** */

    /**
     * 页面布局是否初始化完成
     */
    protected var mIsPrepared = false
    /**
     * 是否是第一次加载数据
     */
    protected var mIsFirstTimeLoadData = true

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mIsFirstTimeLoadData && mIsPrepared) {
            mIsFirstTimeLoadData = false
            getDataLazy()
        }
    }

}


















