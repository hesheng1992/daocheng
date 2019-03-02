package com.a1magway.bgg.p.productReturn;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderCommodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lm on 2018/8/30.
 */
public class PhotoSelectadapter extends BaseRecyclerAdapter<PhotoVH, String> {


    private List<Integer>positions=new ArrayList<>();
    private ItemOperationListener mItemOperationListener;

    private boolean hideSelct;

    public ItemOperationListener getmItemOperationListener() {
        return mItemOperationListener;
    }

    public void setmItemOperationListener(ItemOperationListener mItemOperationListener) {
        this.mItemOperationListener = mItemOperationListener;
    }

    public boolean isHideSelct() {
        return hideSelct;
    }

    public void setHideSelct(boolean hideSelct) {
        this.hideSelct = hideSelct;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    public PhotoSelectadapter(List<String> list) {
        super(list);
    }

    public void setItemOperationListener(ItemOperationListener itemOperationListener) {
        mItemOperationListener = itemOperationListener;
    }

    @Override
    public PhotoVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoVH(parent,0).setItemOperationListener(mItemOperationListener);
    }

    @Override
    public void onRealBindViewHolder(PhotoVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position), this, position);
    }


    /** Item操作事件 */
    public interface ItemOperationListener {
        void onItemClickSelectChange();

    }
}
