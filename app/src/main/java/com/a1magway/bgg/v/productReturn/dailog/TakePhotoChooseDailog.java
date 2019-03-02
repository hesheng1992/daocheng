package com.a1magway.bgg.v.productReturn.dailog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a1magway.bgg.R;

/**
 * Created by enid on 2018/9/5.
 * 选择图片或者拍照对话框
 */

public class TakePhotoChooseDailog extends Dialog {

    private OnClickChooseListener clickChooseListener;

    public OnClickChooseListener getClickChooseListener() {
        return clickChooseListener;
    }

    public void setClickChooseListener(OnClickChooseListener clickChooseListener) {
        this.clickChooseListener = clickChooseListener;
    }

    public TakePhotoChooseDailog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TakePhotoChooseDailog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected TakePhotoChooseDailog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initView(Context context){
        View view=LayoutInflater.from(context).inflate(R.layout.dialog_choose_pic,null);
        setContentView(view);
        TextView textViewPai=(TextView) view.findViewById(R.id.pai);
        TextView textViewGally=(TextView) view.findViewById(R.id.gally);
        TextView textViewCancle=(TextView) view.findViewById(R.id.cancle);
        textViewPai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (clickChooseListener!=null){
                    clickChooseListener.clickTake();
                }
            }
        });
        textViewGally.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (clickChooseListener!=null){
                    clickChooseListener.clickGally();
                }
            }
        });
        textViewCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
    }

    public interface OnClickChooseListener{
        void clickTake();
        void clickGally();
    }
}
