package com.a1magway.bgg.okhttp;


import android.util.Log;

import com.a1magway.bgg.BuildConfig;

import java.util.Locale;




public class LogUtil {
    // open
    private static boolean isLogEnabled = BuildConfig.DEBUG;
    private static final String defaultTag = "";
    private static final String TAG_CONTENT_PRINT = "%s.%s:%d";

    private static StackTraceElement getCurrentStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];

    }

    private static String getContent(StackTraceElement trace) {
        return String.format(Locale.CHINA, TAG_CONTENT_PRINT, trace.getClassName(), trace.getMethodName(),
                trace.getLineNumber());
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (isLogEnabled) {
            Log.d(tag, getContent(getCurrentStackTraceElement()) + "-->" + msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (isLogEnabled) {
            Log.d(tag, getContent(getCurrentStackTraceElement()) + "-->" + msg);
        }
    }

    public static void d(String msg) {
        if (isLogEnabled) {
            Log.d(defaultTag, getContent(getCurrentStackTraceElement()) + "-->" + msg + "");
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isLogEnabled) {
            Log.e(tag, getContent(getCurrentStackTraceElement()) + "-->" + msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (isLogEnabled) {
            Log.e(tag, getContent(getCurrentStackTraceElement()) + "-->" + msg);
        }
    }

    public static void e(String msg) {
        if (isLogEnabled) {
            Log.e(defaultTag, getContent(getCurrentStackTraceElement()) + "-->" + msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isLogEnabled) {
            Log.i(tag, getContent(getCurrentStackTraceElement()) + "-->" + msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (isLogEnabled) {
            Log.i(tag, getContent(getCurrentStackTraceElement()) + "-->" + msg);
        }
    }

    static void apiLogStart(String logLocation, String msg) {
        if (isLogEnabled) {
            Log.e("api", logLocation + " -->" + msg);
        }
    }

    static void apiLogMore(String msg) {
        if (isLogEnabled) {
            Log.e(" -->", msg);
        }
    }
}
