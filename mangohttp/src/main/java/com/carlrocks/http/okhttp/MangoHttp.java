package com.carlrocks.http.okhttp;

import com.alibaba.fastjson.JSON;
import com.carlrocks.http.okhttp.callback.Callback;
import com.carlrocks.http.okhttp.callback.FileCallBack;
import com.carlrocks.http.okhttp.exception.NetRequestException;
import com.carlrocks.http.okhttp.https.HttpsUtils;
import com.carlrocks.http.okhttp.utils.MangoLog;
import okhttp3.*;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;

public final class MangoHttp {

	/**
	 * post request
	 * @param url
	 * @param clazz
	 * @param parameters
	 * @param callBack
	 * @param <T>
	 */
	public static <T> void post(String url, final Class<T> clazz, final RequestHeaderParameters headerParameters,
                                final NetRequestParameters parameters, final RequestCallback<T> callBack) {
		MangoHttpUtils.post().url(url).tag(url).addRequestHeaders(headerParameters).addRequestParams(parameters.getParams()).build().execute(new Callback() {
			@Override
			public Object parseNetworkResponse(Response response, int id) throws Exception {
				return response.body().string();
			}

			@Override
			public void onError(Call call, Exception e, int id) {
				if(callBack != null)callBack.onException(new NetRequestException(e.getMessage()));
			}

			@Override
			public void onResponse(Object response, int id) {
				String responseStr = response.toString();
				logResponse(responseStr);
				T t = JSON.parseObject(responseStr, clazz);
				if(callBack != null)callBack.done(t);
			}
		});
	}

	/**
	 * get request
	 * @param url
	 * @param clazz
	 * @param parameters
	 * @param callBack
	 * @param <T>
	 */
	public static <T> void get(String url, final Class<T> clazz,final RequestHeaderParameters headerParameters,
                                final NetRequestParameters parameters, final RequestCallback<T> callBack) {
		MangoHttpUtils.get().url(url).tag(url).addRequestHeaders(headerParameters).addRequestParams(parameters.getParams()).build().execute(new Callback() {
			@Override
			public Object parseNetworkResponse(Response response, int id) throws Exception {
				return response.body().string();
			}

			@Override
			public void onError(Call call, Exception e, int id) {
				if (callBack != null) callBack.onException(new NetRequestException(e.getMessage()));
			}

			@Override
			public void onResponse(Object response, int id) {
				String responseStr = response.toString();
				logResponse(responseStr);
				T t = JSON.parseObject(responseStr, clazz);
				if (callBack != null) callBack.done(t);
			}
		});
	}


	/**
	 * postContent
	 * @param url
	 * @param clazz
	 * @param content
	 * @param callBack
	 * @param <T>
	 */
	public static <T> void postContent(String url, MediaType mediaType, final Class<T> clazz,final RequestHeaderParameters headerParameters,
							   final String content, final RequestCallback<T> callBack) {
		MangoHttpUtils.postString().url(url).addRequestHeaders(headerParameters).mediaType(mediaType).content(content).build().execute(new Callback() {
			@Override
			public Object parseNetworkResponse(Response response, int id) throws Exception {
				return response.body().string();
			}

			@Override
			public void onError(Call call, Exception e, int id) {
				if (callBack != null) callBack.onException(new NetRequestException(e.getMessage()));
			}

			@Override
			public void onResponse(Object response, int id) {
				String responseStr = response.toString();
				logResponse(responseStr);
				T t = JSON.parseObject(responseStr, clazz);
				if (callBack != null) callBack.done(t);
			}
		});
	}

	/**
	 * Donwload File
	 * @param url
	 * @param callBack
	 */
	public static void donwload(String url, final FileCallBack callBack) {
		MangoHttpUtils.get().url(url).build().execute(callBack);
	}

	public static void initSslSocktFactory(InputStream[] certificates, InputStream bksFile, String password){
		HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
				//其他配置
				.build();
		MangoHttpUtils.initClient(okHttpClient);
	}

	public static void initSslSocktFactory(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager){
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.sslSocketFactory(sslSocketFactory, trustManager)
				//其他配置
				.build();
		MangoHttpUtils.initClient(okHttpClient);
	}
	
	private static void logResponse(String response) {
		MangoLog.json(response);
	}
}
