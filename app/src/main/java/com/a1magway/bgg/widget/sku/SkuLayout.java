package com.a1magway.bgg.widget.sku;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.okhttp.LogUtil;
import com.almagway.common.utils.CollectionUtil;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Set;

import butterknife.ButterKnife;

/**
 * 某个规格列表的所有规格显示的布局
 * Created by jph on 2017/8/9.
 */
public class SkuLayout extends FlexboxLayout {

    private OnSkuSelectedListener mOnSkuSelectedListener;
    private String cate;//颜色、尺寸

    public SkuLayout(Context context) {
        super(context);
    }

    public SkuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置所有显示的规格
     *
     * @param skuSet
     */

    public void setData(@Nullable Set<String> skuSet,String cate) {
        removeAllViews();

        this.cate = cate;
        for (final String sku : skuSet) {
            LogUtil.e("cate1", sku);
            final View itemV = LayoutInflater.from(getContext()).inflate(
                    R.layout.product_detail_item_grid_sku, this, false);
            TextView txt = ButterKnife.findById(itemV, R.id.sku_txt);
            txt.setText(sku);

            itemV.setTag(sku);
            addView(itemV);

            itemV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean newStatus = !itemV.isSelected();
                    if (newStatus) {
                        setSelectedData(sku);
                    } else {
                        //之前是选中，直接取消选中，不需要刷新整个layout
                        itemV.setSelected(false);
                    }
                    if (mOnSkuSelectedListener != null) {
                        mOnSkuSelectedListener.onSkuSelectedChange(sku, newStatus);
                    }
                }
            });
        }
    }

    /**
     * 设置有效的规格数据
     *
     * @param validList
     */
    public void setValidData(@Nullable Set<String> validList) {
        if (cate.equals("颜色")){//颜色分类默认都能点击
            return;
        }
        for (int i = 0, c = getChildCount(); i < c; i++) {
            View itemV = getChildAt(i);
            boolean valid = !CollectionUtil.isEmpty(validList) && validList.contains(itemV.getTag());
            itemV.setEnabled(valid);
        }
    }


    /**
     * 设置全部不选中
     */
    public void setAllNoSelected() {
        for (int i = 0, c = getChildCount(); i < c; i++) {
            View itemV = getChildAt(i);
            itemV.setSelected(false);
        }
    }

    /**
     * 设置选中
     *
     * @param sku
     */
    public void setSelectedData(@Nullable String sku) {
        for (int i = 0, c = getChildCount(); i < c; i++) {
            View itemV = getChildAt(i);
            boolean selected = sku != null && sku.equals(itemV.getTag());
            itemV.setSelected(selected);
        }
    }

    public void setOnSkuSelectedListener(OnSkuSelectedListener onSkuSelectedListener) {
        mOnSkuSelectedListener = onSkuSelectedListener;
    }

    public interface OnSkuSelectedListener {
        void onSkuSelectedChange(String sku, boolean selected);
    }
}
