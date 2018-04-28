package com.carlrocks.httputils.http;

import com.alibaba.fastjson.JSON;
import com.carlrocks.http.okhttp.*;
import com.carlrocks.http.okhttp.callback.FileCallBack;
import com.carlrocks.httputils.config.ServerHost;
import com.carlrocks.httputils.example.RequestParameters;
import okhttp3.MediaType;

import java.util.HashMap;
import java.util.Map;

public class BaseNetworkReq {
	
	protected static <T> void commonRequest(String action, Class<T> clzz, final NetRequestParameters parameters, final PundiCallBack<T> callBack) {
		Map<String, String> headers = new HashMap<>();
		headers.put("loginToken", "50b8984076a13fc1f8436f010408951b");
		headers.put("serialNum", "1234");
		PundiHttp.post(ServerHost.SERVER_URL+"/"+action, clzz, headers, parameters, callBack);
	}

	protected static <T> void commonGetRequest(String action, Class<T> clzz, final NetRequestParameters parameters, final PundiCallBack<T> callBack) {
		Map<String, String> headers = new HashMap<>();
		headers.put("loginToken", "50b8984076a13fc1f8436f010408951b");
		headers.put("serialNum", "1234");
		PundiHttp.get(ServerHost.SERVER_URL+"/"+action, clzz, headers, parameters, callBack);
	}

	public static <T> void postRequest(String url, RequestParameters parameters, final Class<T> clazz, final PundiCallBack<T> callBack){
		Map<String, String> headers = new HashMap<>();
		headers.put("loginToken", "50b8984076a13fc1f8436f010408951b");
		headers.put("serialNum", "1234");
		PundiHttp.postContent(ServerHost.SERVER_URL+url, MediaType.parse("application/json"),clazz, headers,
				JSON.toJSONString(parameters.getParams()),callBack);
	}

	protected static void download(String url, final FileCallBack callBack) {
		MangoHttp.donwload(url, callBack);
	}
}
