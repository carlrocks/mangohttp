package com.carlrocks.http.okhttp.utils;

import com.carlrocks.http.okhttp.logger.*;

/**
 * Created by zhy on 15/11/6.
 */
public class MangoLog
{
    public static String tag = "mangoHttp";
    public static boolean DEBUG = false;

    static {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .logStrategy(new LogcatLogStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(tag)               // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void d(String msg) {
        if (!DEBUG)
            return;
        Logger.d(msg);
    }

    public static void i(String msg) {
        if (!DEBUG)
            return;
        Logger.i(msg);
    }

    public static void e(String msg) {
        if (!DEBUG)
            return;
        Logger.e(msg);
    }

    public static void w(String msg) {
        if (!DEBUG)
            return;
        Logger.w(msg);
    }

    public static void v(String msg) {
        if (!DEBUG)
            return;
        Logger.v(msg);
    }

    public static void json(String msg) {
        if (!DEBUG)
            return;
        Logger.json(msg);
    }
}

