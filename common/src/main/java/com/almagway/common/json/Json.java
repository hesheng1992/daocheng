package com.almagway.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * author: Beaven
 * date: 2017/10/21 22:19
 */

public class Json {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final Gson gson = new GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .create();

    public static  <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static  <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static String getJson(Object obj) {
        return gson.toJson(obj);
    }

}
