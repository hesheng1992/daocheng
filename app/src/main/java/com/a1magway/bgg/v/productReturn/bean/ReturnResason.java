package com.a1magway.bgg.v.productReturn.bean;

/**
 * Created by enid on 2018/9/4.
 */

public class ReturnResason {
    private String reson;
    private boolean isClick;

    public ReturnResason(String reson) {
        this.reson = reson;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
