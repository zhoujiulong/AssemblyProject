package com.zhoujiulong.widgetlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.zhoujiulong.widgetlib.R;


/**
 * @author zhoujiulong
 * @createtime 2019/2/26 11:43
 * LoadingDialog
 */
public class LoadingDialog extends Dialog {

    private ImageView mViewLoading;
    private AnimationDrawable mLoadingAni;

    public static LoadingDialog showLoading(Context context) {
        LoadingDialog loadingDialog = new LoadingDialog(context);
        loadingDialog.setContentView(R.layout.widget_dialog_loading);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(true);
        loadingDialog.show();
        return loadingDialog;
    }

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.WidgetLoadingDialog);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void show() {
        super.show();
        if (mViewLoading == null) {
            mViewLoading = findViewById(R.id.iv_loading);
            mViewLoading.setImageResource(R.drawable.widget_anim_loading);
        }
        if (mLoadingAni == null) {
            mLoadingAni = (AnimationDrawable) mViewLoading.getDrawable();
        }
        mLoadingAni.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mLoadingAni.stop();
    }

    @Override
    public void cancel() {
        super.cancel();
        mLoadingAni.stop();
    }
}





















