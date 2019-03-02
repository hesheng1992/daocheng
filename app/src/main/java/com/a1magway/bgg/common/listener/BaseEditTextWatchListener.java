package com.a1magway.bgg.common.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * author: Beaven
 * date: 2017/10/17 19:33
 */

public abstract class BaseEditTextWatchListener implements TextWatcher {
    private int position;

    public void updatePosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
