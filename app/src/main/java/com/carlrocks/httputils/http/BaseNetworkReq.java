package com.carlrocks.httputils.http;

import com.carlrocks.http.okhttp.MangoHttp;
import com.carlrocks.http.okhttp.NetRequestParameters;
import com.carlrocks.http.okhttp.RequestCallback;
import com.carlrocks.http.okhttp.callback.FileCallBack;
import com.carlrocks.httputils.config.ServerHost;

public class BaseNetworkReq {
	
	protected static <T> void commonRequest(String action, Class<T> clzz, final NetRequestParameters parameters, final RequestCallback<T> callBack) {
		MangoHttp.post(ServerHost.SERVER_URL+"/"+action, clzz, parameters, callBack);
	}

	protected static <T> void commonGetRequest(String action, Class<T> clzz, final NetRequestParameters parameters, final RequestCallback<T> callBack) {
		MangoHttp.get(ServerHost.SERVER_URL+"/"+action, clzz, parameters, callBack);
	}

	protected static void download(String url, final FileCallBack callBack) {
		MangoHttp.donwload(url, callBack);
	}
}
