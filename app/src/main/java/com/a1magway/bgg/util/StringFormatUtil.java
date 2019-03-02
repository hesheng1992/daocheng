package com.a1magway.bgg.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import com.a1magway.bgg.R;
import java.text.DecimalFormat;

/** 得到固定格式的字符串 Created by jph on 2015/10/15. */
public class StringFormatUtil {

    /**
     * 价格格式，保留2位小数
     *
     * @param context
     * @param price
     * @return
     */
    public static String getPrice(Context context, double price) {
        DecimalFormat mDoubleFormat = new DecimalFormat("0.##");
        return getPrice(context, mDoubleFormat.format(price));
    }

    public static String getPrice(Context context, Object price) {
        Double d = Double.valueOf(String.valueOf(price));
        DecimalFormat mDoubleFormat = new DecimalFormat("0.00");
        return String.format(context.getString(R.string.format_price), mDoubleFormat.format(d));
    }

    /**
     * 获取中文格式的价钱字符串，自动追加“元”
     *
     * @param context
     * @param price
     * @return
     */
    public static String getCNPrice(Context context, double price) {
        DecimalFormat mDoubleFormat = new DecimalFormat("0.##");
        return String.format(
                context.getString(R.string.format_price_cn), mDoubleFormat.format(price));
    }

    /**
     * 自动去小数点
     *
     * @param number
     * @return
     */
    public static String getPriceFormatNumber(double number) {
        DecimalFormat mDoubleFormat = new DecimalFormat("0.##");
        return mDoubleFormat.format(number);
    }

    /**
     * 显示倒计时的显示样式HH:mm:ss
     *
     * @param diffS 剩下的时间 ms
     * @return
     */
    public static String getCountDownTimeStr(long diffS) {
        if (diffS < 0) {
            return "00:00:00";
        }

        long s = diffS % 60;
        long m = (diffS % (60 * 60)) / 60;
        long h = diffS / (60 * 60);

        StringBuilder sb = new StringBuilder();
        return sb.append(getCountDownPlace(h))
                .append(":")
                .append(getCountDownPlace(m))
                .append(":")
                .append(getCountDownPlace(s))
                .toString();
    }

    /**
     * 处理某一位的显示，不足两位添加0前缀
     *
     * @param i
     * @return
     */
    private static String getCountDownPlace(long i) {
        if (i < 10) {
            return "0" + i;
        }

        if (i > 99) {
            i = 99;
        }

        return String.valueOf(i);
    }

    /**
     * 常用的格式化方式
     *
     * @param context
     * @param strId
     * @param args
     * @return
     */
    public static String format(@NonNull Context context, @StringRes int strId, Object... args) {
        return String.format(context.getString(strId), args);
    }
}
