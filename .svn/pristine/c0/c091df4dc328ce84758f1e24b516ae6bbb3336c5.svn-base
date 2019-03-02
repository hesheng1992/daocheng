package com.a1magway.bgg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lyx on 2017/8/8.
 */
public class DateUtils {

    public static final String DATE_CHINESE = "yyyy 年 MM 月 dd 日 ";
    public static final String TIME_DEFAULT_ONE = "yyy-MM-dd HH:mm:ss";
    public static final String DATE_ONE = "yyyMMdd";

    public static Date formatDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 日期数据转格林尼治时间
     *
     * @param str    日期数据
     * @param format 日期格式
     * @return 毫秒数
     */
    public static long formatMillions(String str, String format) {
        Date date = formatDate(str, format);
        return date.getTime();
    }

    /**
     * 日期格式转换
     *
     * @param rawDate   日期数据
     * @param rawFormat 日期数据格式
     * @param desFormat 目标日期格式
     * @return 目标日期数据
     */
    public static String dateFormatConversion(String rawDate, String rawFormat, String desFormat) {
        Date date = formatDate(rawDate, rawFormat);
        return formatString(date, desFormat);
    }
}
