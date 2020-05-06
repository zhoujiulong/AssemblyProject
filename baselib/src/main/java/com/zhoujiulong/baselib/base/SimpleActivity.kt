package com.zhoujiulong.baselib.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.zhoujiulong.baselib.app.ActivityFragmentManager

/**
 * Author : zhoujiulong
 * Email : 754667445@qq.com
 * Time : 2017/03/24
 * Desc : SimpleActivity
 */
abstract class SimpleActivity : FragmentActivity(), View.OnClickListener {

    /**
     * 網絡請求標記tag
     */
    val ReTag = System.currentTimeMillis().toString()
    private var mISFirstResume = true

    protected lateinit var mContext: Context
    protected lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ActivityFragmentManager.getInstance().addActivity(this)

        mContext = this
        mActivity = this
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
        }
    }

    override fun onDestroy() {
        ActivityFragmentManager.getInstance().removeActivity(this)
        detachView()
        super.onDestroy()
    }

    /* ********************************************** 初始化相关方法 **************************************************** */
    /* ********************************************** 初始化相关方法 **************************************************** */

    /**
     * 获取布局文件资源 id
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
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 获取数据
     */
    protected abstract fun getData()

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

    /* ********************************************** 点击输入框外部隐藏软键盘 **************************************************** */
    /* ********************************************** 点击输入框外部隐藏软键盘 **************************************************** */

    /**
     * 点击输入框外部是否要隐藏键盘，默认为 true
     */
    protected var mNeedClickOutHideInput = true

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (mNeedClickOutHideInput && ev.action == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v!!)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v     View
     * @param event Event
     * @return boolean
     */
    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param view View
     */
    fun hideSoftInput(view: View) {
        val token = view.windowToken
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }


}













