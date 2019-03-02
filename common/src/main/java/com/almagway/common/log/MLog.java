package com.almagway.common.log;

import android.support.annotation.NonNull;

import java.util.Locale;

import timber.log.Timber;

/**
 * author: Beaven
 * date: 2017/10/21 21:07
 */

public class MLog {

    private MLog() {
        throw new UnsupportedOperationException("MLog class can not instance");
    }

    /*
     * 初始化
     */
    public static void plat(Timber.Tree tree) {
        Timber.plant(tree);
    }

    public static void i(@NonNull String msg) {
        Timber.tag(getTag(true)).i(msg);
    }

    public static void i(@NonNull String tag, String msg) {
        Timber.tag(tag + getTag(false)).i(msg);
    }

    public static void i(@NonNull String tag, String msg, Object... args) {
        Timber.tag(tag + getTag(false)).i(msg, args);
    }

    public static void d(@NonNull String msg) {
        Timber.tag(getTag(true)).d(msg);
    }

    public static void d(@NonNull String tag, String msg) {
        Timber.tag(tag + getTag(false)).d(msg);
    }

    public static void d(@NonNull String tag, String msg, Object... args) {
        Timber.tag(tag + getTag(false)).d(msg, args);
    }

    public static void e(@NonNull String msg) {
        Timber.tag(getTag(false)).e(msg);
    }

    public static void e(@NonNull String tag, String msg) {
        Timber.tag(tag + getTag(false)).e(msg);
    }

    public static void e(@NonNull String tag, String msg, Object... args) {
        Timber.tag(tag + getTag(false)).e(msg, args);
    }

    public static void e(String msg, Throwable t) {
        Timber.tag(msg).e(t);
    }

    /**
     * 补充Log的tag
     */
    private static String getTag(boolean showClassName) {
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        String className = thisMethodStack.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1, className.length());
        String fileName = thisMethodStack.getFileName();
        int lineNumber = thisMethodStack.getLineNumber();
        String tag;
        if (showClassName) {
            tag = String.format(Locale.getDefault(), "%s(%s:%d)", className, fileName, lineNumber);
        } else {
            tag = String.format(Locale.getDefault(), "(%s:%d)", fileName, lineNumber);
        }
        return tag;
    }
}
