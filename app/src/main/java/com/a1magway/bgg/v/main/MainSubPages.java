package com.a1magway.bgg.v.main;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.a1magway.bgg.v.main.MainSubPages.CART;
import static com.a1magway.bgg.v.main.MainSubPages.CATE;
import static com.a1magway.bgg.v.main.MainSubPages.FOUND;
import static com.a1magway.bgg.v.main.MainSubPages.HOME;
import static com.a1magway.bgg.v.main.MainSubPages.MAIN_HOME;
import static com.a1magway.bgg.v.main.MainSubPages.PERSONAL;

/**
 * 主页的子页面的name枚举
 * Created by jph on 2017/7/20.
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ MAIN_HOME,HOME, CATE,FOUND, CART, PERSONAL })
public @interface MainSubPages {
    String MAIN_HOME = "mainhome";
    String HOME = "home";
    String CATE = "cate";
    String FOUND="found";
    String CART = "cart";
    String PERSONAL = "personal";
}
