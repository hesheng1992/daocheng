package com.a1magway.bgg.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.widget.sku.AllSkuLayout;
import com.almagway.common.utils.ToastUtil;

import java.io.Serializable;
import java.util.Map;

import io.reactivex.Observable;

/**
 * 产品规格选择页面
 */
public class ProductChooseGuiGeDialpg extends Dialog {

    private AllSkuLayout mAllSkuLayout;

    private OnClickQueRenListenr onClickQueRenListenr;

    private TextView textNumber;

    private TextView pro_txt_stock;

    private int num=0;
    //拼团商品数量不能增加或删除
    private boolean isNoUpdate=false;

    public void setOnClickQueRenListenr(OnClickQueRenListenr onClickQueRenListenr) {
        this.onClickQueRenListenr = onClickQueRenListenr;
    }

    public ProductChooseGuiGeDialpg(@NonNull Context context) {
        this(context,R.style.alert_dialog_style2);
    }

    public ProductChooseGuiGeDialpg(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    //加载布局
    public void initView(Context context){
        View view=LayoutInflater.from(context).inflate(R.layout.dialog_product_guige_choose,null);
        mAllSkuLayout=view.findViewById(R.id.info_layout_all_sku);
        TextView textView=view.findViewById(R.id.text_queren);
        ImageView jianImage=view.findViewById(R.id.pro_img_reduce);//减
        textNumber=view.findViewById(R.id.pro_txt_count);//数量
        ImageView pro_img_plus=view.findViewById(R.id.pro_img_plus);//加
        pro_txt_stock=view.findViewById(R.id.pro_txt_stock);//库存
        jianImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(textNumber.getText().toString());
                if (!isNoUpdate){
                    if (i==0 || i==1){
//                        ToastUtil.showShort("购买数量不能大于库存!");
                    }else{
                        i-=1;
                        textNumber.setText(i+"");
                    }
                }
            }
        });

        pro_img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(textNumber.getText().toString());
                if (!isNoUpdate){
                    if (i<num){
                        i+=1;
                        textNumber.setText(i+"");
                    }else{
                        ToastUtil.showShort("购买数量不能大于库存数量!");
                    }
                }else{
                    ToastUtil.showShort("限购一个");
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onClickQueRenListenr!=null){
                    int i = Integer.parseInt(textNumber.getText().toString());
                    num=0;
                    onClickQueRenListenr.onClickQueRen(i);
                }
            }
        });
        setContentView(view);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        //这句就是设置dialog横向满屏了。
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

    }

    /**
     * 设置数据
     * @param allSkuObservable
     */
    public void setmAllSkuLayoutData(Observable<Map<String, String>> allSkuObservable){
        if (mAllSkuLayout!=null){
            mAllSkuLayout.setData(allSkuObservable);
        }
    }

    public AllSkuLayout getAllSkuLayout(){
        if (mAllSkuLayout!=null){
            return mAllSkuLayout;
        }
        return null;
    }

    public interface OnClickQueRenListenr{
        Serializable onClickQueRen(int num);
    }

    /**
     * 更新库存
     * @param num
     */
    public void upDateNumber(int num,boolean isFlag){
        //是否需要显示库存
        if (!isFlag){
            pro_txt_stock.setVisibility(View.GONE);
        }else{
            pro_txt_stock.setVisibility(View.VISIBLE);
        }
        if (num==0){
            pro_txt_stock.setText("售罄");
        }else{
            pro_txt_stock.setText("剩余库存: "+num);
        }
        this.num=num;
        textNumber.setText("1");
    }

    @Override
    public void show() {
        super.show();
        textNumber.setText("1");
    }


    /**
     * 设置拼团数量不能更新
     */
    public void setNumNoUpdate(boolean isNoUpdate){
        this.isNoUpdate=isNoUpdate;
    }
}
