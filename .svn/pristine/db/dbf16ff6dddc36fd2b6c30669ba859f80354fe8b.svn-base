package com.a1magway.bgg.widget.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.v.productReturn.ReturnContentActivity;


/**
 * Created by lm on 2018/8/31.
 */
public class DialogUtil {

    public static AlertDialog dialog;
    private DialogOnclickBtnlistener OnclickBtnlistener;
    private TextView textView;
    private TextView okBtn;
    private TextView cancleBtn;
    private PActivity context;




    public DialogUtil(PActivity context,  DialogOnclickBtnlistener dialogOnclickBtnlistener){
        this.context=context;
        OnclickBtnlistener=dialogOnclickBtnlistener;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_tip_layout,null);
        textView=view.findViewById(R.id.dialog_text_content);
        okBtn=view.findViewById(R.id.dialog_ok);
        cancleBtn=view.findViewById(R.id.dialog_cancle);
        builder.setView(view);
        dialog=builder.create();
        dialog.setCancelable(false);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnclickBtnlistener.onClickOk();
                dialog.dismiss();
                setBackgroundAlpha(1f);
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                setBackgroundAlpha(1f);
            }
        });

    }


    public  void showDialog(String content){
        setBackgroundAlpha(0.5f);
        textView.setText(content);
        dialog.show();
    }

    public  void dissDialog(){
        dialog.dismiss();
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
    }
}
