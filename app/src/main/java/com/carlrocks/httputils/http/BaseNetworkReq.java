package com.carlrocks.httputils.http;

import com.carlrocks.http.okhttp.*;
import com.carlrocks.http.okhttp.callback.FileCallBack;
import com.carlrocks.http.okhttp.json.RequestCallBack;
import com.carlrocks.httputils.config.ServerHost;
import com.carlrocks.httputils.example.RequestParameters;

import okhttp3.MediaType;

public class BaseNetworkReq {
	
	protected static <T> void commonRequest(String action, final NetRequestParameters parameters, final RequestCallBack<T> callBack) {
		MangoHttp.post(ServerHost.SERVER_URL+"/"+action, new HeaderParameters(), parameters, callBack);
	}

	protected static <T> void commonGetRequest(String action, final NetRequestParameters parameters, final RequestCallBack<T> callBack) {
		MangoHttp.get(ServerHost.SERVER_URL+"/"+action, new HeaderParameters(), parameters, callBack);
	}

	public static <T> void postRequest(String url, RequestParameters parameters, final RequestCallBack<T> callBack){
		MangoHttp.postContent(ServerHost.SERVER_URL+ "/" +url, MediaType.parse("application/json"), new HeaderParameters(), parameters.toJson(),callBack);
	}

	protected static void download(String url, final FileCallBack callBack) {
		MangoHttp.donwload(url, callBack);
	}
}
