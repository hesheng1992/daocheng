package com.a1magway.bgg.v.articleManager;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/8/22.
 */

public interface AticleManagerContract {
    interface View extends ILoadingV{

        void setAdapter(RecyclerView.Adapter adapter);

        void showCreateAticle();
    }


    interface Presenter extends BaseContract.BasePresenter {

    }
}
