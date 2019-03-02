package com.a1magway.bgg.common;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 *
 * Created by jph on 2017/3/24.
 */
public abstract class BaseViewOwner {
    private View mView;

    public BaseViewOwner(View view) {
        mView = view;
        ButterKnife.bind(this, view);
    }

    public BaseViewOwner(@NonNull ViewGroup parent, @LayoutRes int layoutRes) {
        this(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    public BaseViewOwner(Context context, @LayoutRes int layoutRes) {
        this(LayoutInflater.from(context).inflate(layoutRes, null));
    }

    protected Context getContext() {
        return getView().getContext();
    }

    public View getView() {
        return mView;
    }

}
