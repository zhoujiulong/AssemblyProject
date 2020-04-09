package com.zhoujiulong.baselib.base

import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhoujiulong.baselib.LoadingDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity<T : BasePresenter<*, *>> : SimpleActivity(),
    BaseView {

    /**
     * 用于添加rx的监听的在onDestroy中记得关闭不然会内存泄漏。
     */
    private var mCompositeDisposable: CompositeDisposable? = null

    protected var mPresenter: T? = null
    private var mLoadingDialog: LoadingDialog? = null

    override fun onDestroy() {
        mCompositeDisposable?.apply { dispose() }
        mLoadingDialog?.apply { if (isShowing) dismiss() }
        super.onDestroy()
    }

    public override fun attachView() {
        mPresenter?.apply { attachView(this@BaseActivity, ReTag) }
    }

    public override fun detachView() {
        mPresenter?.apply { detachView() }
    }

    override fun bindSubscription(disposable: Disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = CompositeDisposable()
        }
        this.mCompositeDisposable?.add(disposable)
    }

    override fun showLoading() {
        if (mLoadingDialog == null) mLoadingDialog = LoadingDialog.build(this)
        mLoadingDialog?.show()
    }

    override fun hideLoading() {
        mLoadingDialog?.dismiss()
    }

    /**
     * 请求权限
     */
    fun requestPermission(vararg permissions: String, block: (success: Boolean) -> Unit) {
        val list = mutableListOf<String>()
        permissions.forEach { list.add(it) }
        requestPermission(list, 0, true, block)
    }

    private fun requestPermission(
        permissions: List<String>, index: Int,
        allBeforeSuccess: Boolean, block: (success: Boolean) -> Unit
    ) {
        val dis = RxPermissions(this).request(permissions[index]).subscribe {
            if (index == permissions.size - 1) {
                block(it && allBeforeSuccess)
            } else {
                requestPermission(permissions, index + 1, allBeforeSuccess && it, block)
            }
        }
        bindSubscription(dis)
    }
}









