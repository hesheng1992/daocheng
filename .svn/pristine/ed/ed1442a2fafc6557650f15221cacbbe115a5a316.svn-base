package com.a1magway.bgg.p.search;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.FilterCate1;

import butterknife.BindView;

/**
 * Created by jph on 2017/8/3.
 */
public class FilterCate1VH extends BaseRecyclerVH<FilterCate1> {

    @BindView(R.id.cate_txt_name)
    TextView mNameTxt;

    public FilterCate1VH(@NonNull ViewGroup parent) {
        super(parent, R.layout.search_filter_item_list_cate);
    }

    @Override
    public void showViewContent(FilterCate1 filterCate1) {
        super.showViewContent(filterCate1);

        mNameTxt.setText(filterCate1.getName());
    }

    public void setEnable(boolean enable) {
        mNameTxt.setEnabled(enable);
    }
}
