package com.a1magway.bgg.p.seckill;

import android.util.SparseArray;
import android.view.ViewGroup;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.tool.TimeCountDownController;
import java.util.List;

/** 秒杀商品列表适配器 Created by jph on 2017/7/28. */
public class ProductGridAdapter extends BaseRecyclerAdapter<ProductVH, ProductItem> {

    private SparseArray<TimeCountDownController> controllerMap = new SparseArray<>();

    public ProductGridAdapter(List<ProductItem> list) {
        super(list);
    }

    @Override
    public ProductVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductVH(parent);
    }

    @Override
    public void onRealBindViewHolder(ProductVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(position, getItem(position));
        removeCountdownController(position);
        TimeCountDownController countDownController =
                holder.createCountdownController(position, getItem(position), this);
        if (countDownController != null) {
            countDownController.start();
            addCountdownController(position, countDownController);
        }
    }

    public void clearCountdownControllerMap() {
        int size = controllerMap.size();
        for (int i = 0; i < size; i++) {
            TimeCountDownController countDownController = controllerMap.valueAt(i);
            if (countDownController != null) {
                countDownController.cancel();
            }
        }
        controllerMap.clear();
    }

    public void addCountdownController(int pos, TimeCountDownController countDownController) {
        controllerMap.put(pos, countDownController);
    }

    public void removeCountdownController(int pos) {
        TimeCountDownController countDownController = controllerMap.get(pos);
        if (countDownController != null) {
            countDownController.cancel();
        }
        controllerMap.remove(pos);
    }
}
