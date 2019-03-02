package com.a1magway.bgg.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * 旧的Adapter 逐步把它给清除掉
 */
@Deprecated
public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, T>
        extends RxRecyclerAdapter<VH> implements IAdapterData<T> {

    public List<T> mListData;
    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }

    public BaseRecyclerAdapter(List<T> list) {
        mListData = list;
    }

    @Override
    public void onRealBindViewHolder(VH holder, int position) {
        bindItemListener(holder, position);
    }

    @Override
    public List<T> getList() {
        return mListData;
    }

    @Override
    public void addList(List<? extends T> list) {
        if (mListData != null) {
            mListData.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void setList(List<? extends T> listData) {
        if (mListData != null) {
            mListData.clear();
            if (listData != null) {
                mListData.addAll(listData);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public void clearList() {
        if (mListData != null) {
            mListData.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public T getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public void addItem(T item) {
        if (mListData != null) {
            mListData.add(item);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeItem(T item) {
        if (mListData != null) {
            mListData.remove(item);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeItem(int position) {
        if (mListData != null) {
            mListData.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getRealItemCount() {
        if (mListData == null) {
            return 0;
        }
        return mListData.size();
    }

    private void bindItemListener(VH holder, int position) {
        final int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(view, finalPosition);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mListener != null) {
                    mListener.onItemLongClick(view, finalPosition);
                }

                return false;
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public OnItemClickListener getOnItemClickListener() {
        return mListener;
    }


}
