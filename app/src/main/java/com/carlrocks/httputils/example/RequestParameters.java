package com.carlrocks.httputils.example;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.carlrocks.http.okhttp.NetRequestParameters;


/**
 * Created by wokoworks on 2017/5/5.
 */

public class RequestParameters extends NetRequestParameters{

    // 手机品牌
    public static String MANUFACTURER = Build.MANUFACTURER;
    // 手机型号
    public static String MODEL = Build.MODEL;
    // 操作系统
    public static String OS = "Android";
    // 操作系统版本号 19 20
    public static String VERSION_OS = Build.VERSION.SDK;
    // 系统版本号4.4 5.0
    public static String SYSTEM_OS = Build.VERSION.RELEASE;
    // 手机唯一标识码
    public static String IMEI = "";
    // UUID标识码
    public static String UUID = "";
    // 项目版本号：
    public static String VERSION_PRO;
    //API版本号
    public static String VERSION_API = "1";
    // 标识是哪个应用市场
    public static String CHANNEL = "WEB";
    // 手机号码
    public static String TEL = "";
    // 上下文
    public static Context context;
    // 返回当前手机语言 en or cn
    public static String language = null;
    //屏幕分辨率Width
    public static int screenWidth = 720;
    //屏幕分辨率Height
    public static int screenHeight = 1280;
    //获取状态栏Height
    public static int statusBarHeight = 0;
    //每页显示的数据条数
    public static final int pageRecords = 20;
    //第一次登陆的常量
    public static int isFirstPlay = 0;
    //服务唯一码
    public static String uniqueID = IMEI;
    //用户Agent
    public static String userAgent;
    //用户Agent
    public static boolean userCanSim;
    //接口版本号
    public static int web_version = 1;
    //国家码
    public static String countryCode = "ID"; //ID
    //运营商
    public static String OPERATOR = "pundi_null";

    private static RequestParameters requestParameters;

    // 获取必须的基本参数
    public static RequestParameters getBasicParameters() {
        requestParameters = new RequestParameters();
        requestParameters.put("version_pro", VERSION_PRO);
        requestParameters.put("version_api", VERSION_API);
        requestParameters.put("manufacturer", MANUFACTURER);
        requestParameters.put("model", !TextUtils.isEmpty(MODEL) ? MODEL.trim() : MODEL);
        requestParameters.put("os", OS);
        requestParameters.put("version_os", VERSION_OS);
        requestParameters.put("channel", CHANNEL);
        return requestParameters;
    }
}
