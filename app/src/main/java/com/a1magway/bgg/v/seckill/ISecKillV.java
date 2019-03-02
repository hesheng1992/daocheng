package com.a1magway.bgg.v.seckill;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;

/**
 * 对应秒杀专区列表子界面的View层
 * Created by jph on 2017/7/28.
 */
public interface ISecKillV extends ILoadingV {

    void setRecyclerViewAdapter(BaseRecyclerAdapter adapter);


    void stopRefresh();

    void stopLoadMore();

    void setLoadable(boolean loadable);
}
