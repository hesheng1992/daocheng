package com.a1magway.bgg.p.collection;

import android.util.Log;
import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.CollectionData;
import com.a1magway.bgg.data.repository.ICollectionListData;

import java.util.List;

/**
 * Created by enid on 2018/6/4.
 */

public class ProductCollectionListAdapter extends BaseRecyclerAdapter<CollectionVH,CollectionData>{
    private ICollectionListData mICollectionData;
    private boolean mShowRadioButton;
    private CollectionVH mCollectionVH;
    private ItemClickListener itemClickListener;
    public ProductCollectionListAdapter(List<CollectionData> list,ICollectionListData collectionListData) {
        super(list);
        mICollectionData = collectionListData;
        Log.d("enid","ProductCollectionListAdapter execute");

    }

    @Override
    public CollectionVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectionVH(parent,mICollectionData);
    }

    public void addItemBoxListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onRealBindViewHolder(CollectionVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        this.mCollectionVH = holder;
        holder.showViewContent(getItem(position),this,position,mShowRadioButton,itemClickListener);
    }

    public void setShowRadioButton(boolean showRadioButton){
        mShowRadioButton = showRadioButton;
    }

    public List<Integer> getSelectedProductId(){
       return mCollectionVH.getSelectedProductIds();
    }

    /** Item操作事件 */
    public interface ItemClickListener {
        void onItemClickCheckedBox(int position);
    }
}
