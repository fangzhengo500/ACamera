package com.loosu.acamera.utils;


/**
 * Coding by LuWei.
 * Created on 2017/4/12.
 */

public class Log {
    private static final String TAG_default = "ILog --->";

    public static void e(String msg) {
        android.util.Log.e(TAG_default, msg + ". " + GetFileLine());
    }

    public static void e(String msg, Throwable throwable) {
        android.util.Log.e(TAG_default, msg + ". " + GetFileLine(), throwable);
    }

    public static void i(String msg) {
        android.util.Log.i(TAG_default, msg + ". " + GetFileLine());
    }

    public static void w(String msg) {
        android.util.Log.w(TAG_default, msg + ". " + GetFileLine());
    }

    public static void v(String msg) {
        android.util.Log.v(TAG_default, msg + ". " + GetFileLine());
    }

    public static void d(String msg) {
        android.util.Log.d(TAG_default, msg + ". " + GetFileLine());
    }

    public static void d(String msg, Throwable throwable) {
        android.util.Log.d(TAG_default, msg + ". " + GetFileLine(), throwable);
    }

    public static void e(String TAG, String msg) {
        android.util.Log.e(TAG, msg + ". " + GetFileLine());
    }

    public static void e(String TAG, String msg, Throwable throwable) {
        android.util.Log.e(TAG, msg, throwable);
    }

    public static void i(String TAG, String msg) {
        android.util.Log.i(TAG, msg + ". " + GetFileLine());
    }

    public static void w(String TAG, String msg) {
        android.util.Log.w(TAG, msg + ". " + GetFileLine());
    }

    public static void v(String TAG, String msg) {
        android.util.Log.v(TAG, msg + ". " + GetFileLine());
    }

    public static void d(String TAG, String msg) {
        android.util.Log.d(TAG, msg + ". " + GetFileLine());
    }

    public static void d(String TAG, String msg, Throwable throwable) {
        android.util.Log.d(TAG, msg + ". " + GetFileLine(), throwable);
    }

    private static String GetFileLine() {
        StackTraceElement StackTraceElementObj = (new Throwable()).getStackTrace()[2];

        String wholeStr = StackTraceElementObj.toString();
        // 截取括号部分
        int index = wholeStr.indexOf("(");
        return wholeStr.substring(index);
    }
}
