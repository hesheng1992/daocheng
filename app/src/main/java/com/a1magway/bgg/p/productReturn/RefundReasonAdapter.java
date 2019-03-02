package com.a1magway.bgg.p.productReturn;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;

import java.util.List;

/**
 * Created by lm on 2018/8/31.
 */
public class RefundReasonAdapter  extends BaseRecyclerAdapter<RefundReasonAdapter.ReFundReasonVH,String> {
    public RefundReasonAdapter(List<String> list) {
        super(list);
    }

    @Override
    public ReFundReasonVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReFundReasonVH(parent, R.layout.item_refund_reason_layout);
    }

    class  ReFundReasonVH extends BaseRecyclerVH<String>{

        public ReFundReasonVH(@NonNull ViewGroup parent, int layoutResId) {
            super(parent, layoutResId);
        }
    }
}
