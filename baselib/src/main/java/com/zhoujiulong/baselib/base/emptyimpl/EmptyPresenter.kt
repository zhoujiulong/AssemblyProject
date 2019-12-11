package com.zhoujiulong.baselib.base.emptyimpl

import com.zhoujiulong.baselib.base.BasePresenter


/**
 * @author zhoujiulong
 * @createtime 2019/2/27 11:34
 * 空类，占位用
 */
class EmptyPresenter : BasePresenter<EmptyModel, com.zhoujiulong.baselib.base.emptyimpl.EmptyContract.IView>(),
    com.zhoujiulong.baselib.base.emptyimpl.EmptyContract.IPresenter {

    override fun initModel(): EmptyModel {
        return EmptyModel()
    }

}
