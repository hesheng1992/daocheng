package com.a1magway.bgg.p.cate;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.CateItem;

import java.util.List;

/**
 * 分类列表的适配器
 * Created by jph on 2017/7/21.
 */
public class CatesAdapter extends BaseRecyclerAdapter<BaseRecyclerVH<CateItem>, CateItem> {
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_TOP = 1;

    public CatesAdapter(List<CateItem> list) {
        super(list);
    }

    @Override
    public int getRealItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_TOP;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    public BaseRecyclerVH<CateItem> onRealCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case ITEM_TYPE_NORMAL:
//                return new CateVH(parent);
//            case ITEM_TYPE_TOP:
//                return new TopCateVH(parent);
//            default:
//                return new CateVH(parent);
//        }
        return new CateVH(parent);
    }

    @Override
    public void onRealBindViewHolder(BaseRecyclerVH<CateItem> holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }
}
