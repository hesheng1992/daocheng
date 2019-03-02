package com.almagway.common.utils;

import android.text.TextUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.almagway.common.utils.CollectionUtil.getSize;

/**
 * Created by jph on 2016/4/15.
 */
public class StringUtil {

    public static boolean isNotEmpty(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isEmpty(String... str) {
        for (String s : str) {
            if (TextUtils.isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 捕获了异常的转换int方法
     */
    public static int parseInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i = 0;

        try {
            i = Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static double parseDouble(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        double d = 0;

        try {
            d = Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 将集合组装成key分隔的字符串
     */
    public static String combineList(List<?> list, String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, c = getSize(list); i < c; i++) {
            if (i == 0) {
                sb.append(list.get(i).toString());
            } else {
                sb.append(key);
                sb.append(list.get(i).toString());
            }
        }
        return sb.toString();
    }
    public static boolean isMobileNumber(String mobiles) {
        if(isEmpty(mobiles))
            return false;
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isZipNO(String zipString){
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }
}
