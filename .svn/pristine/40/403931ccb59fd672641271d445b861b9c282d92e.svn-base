package com.almagway.common.utils;

import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * Collection工具类
 * Created by jph on 2016/4/12.
 */
public class CollectionUtil {

    /**
     * 判断Collection是否无数据
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 得到Collection的数据数量，null情况下返回0
     */
    public static int getSize(Collection collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * List，判断了是否为空
     */
    public static <T> T getListLastElement(@Nullable List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(list.size() - 1);
    }
}
