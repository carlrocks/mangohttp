package com.carlrocks.http.okhttp;

import com.alibaba.fastjson.JSON;
import com.carlrocks.http.okhttp.callback.Callback;
import com.carlrocks.http.okhttp.callback.FileCallBack;
import com.carlrocks.http.okhttp.https.HttpsUtils;
import com.carlrocks.http.okhttp.utils.MangoLog;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;


/**
 * Created by mangohttp on 2018/5/4.
 * })
 */

public abstract class PundiHttp {

    /**
     * post request
     * @param url
     * @param clazz
     * @param parameters
     * @param callBack
     * @param <T>
     */
    public static <T> void post(String url, final Class<T> clazz, final RequestHeaderParameters headers,
                                final NetRequestParameters parameters, final PundiCallBack<T> callBack) {
        MangoHttpUtils.post().url(url).tag(url).addRequestHeaders(headers).addRequestParams(parameters.getParams()).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return response.body().string();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (callBack != null) {
                    callBack.onError(500, "Network error");
                }
            }

            @Override
            public void onResponse(Object response, int id) {
                String responseStr = response.toString();
                MangoLog.json(response.toString());
                try {
                    int code = new JSONObject(responseStr).optInt("code");
                    if (code == 200) {
                        Object t = JSON.parseObject(responseStr, clazz);
                        if (callBack != null) {
                            callBack.onSuccess((T) t);
                        }
                    } else {
                        callBack.onError(code, new JSONObject(responseStr).optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callBack != null) {
                        callBack.onError(500, "Network error");
                    }
                    MangoLog.e(e.getMessage());
                }
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
    public static <T> void get(String url, final Class<T> clazz, final RequestHeaderParameters headers,
                               final NetRequestParameters parameters, final PundiCallBack<T> callBack) {
        MangoHttpUtils.get().url(url).tag(url)
                .addRequestHeaders(headers)
                .addRequestParams(parameters.getParams()).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return response.body().string();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (callBack != null) {
                    callBack.onError(500, "Network error");
                }
            }

            @Override
            public void onResponse(Object response, int id) {
                String responseStr = response.toString();
                MangoLog.json(response.toString());
                try {
                    int code = new JSONObject(responseStr).optInt("code");
                    if (code == 200) {
                        Object t = JSON.parseObject(responseStr, clazz);
                        if (callBack != null) {
                            callBack.onSuccess((T) t);
                        }
                    } else {
                        callBack.onError(code, new JSONObject(responseStr).optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callBack != null) {
                        callBack.onError(500, "Network error");
                    }
                    MangoLog.e(e.getMessage());
                }
            }
        });
    }

    public static <T> void postContent(String url, MediaType mediaType, final Class<T> clazz, final RequestHeaderParameters headers, String content, final PundiCallBack<T> callBack) {
        MangoHttpUtils.postString().url(url).tag(url).addRequestHeaders(headers)
                .mediaType(mediaType).content(content).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return response.body().string();
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                MangoLog.e(e.getMessage());
                if (callBack != null) {
                    callBack.onError(500, "Network error");
                }
            }

            @Override
            public void onResponse(Object response, int i) {
                String responseStr = response.toString();
                MangoLog.json(response.toString());
                try {
                    int code = new JSONObject(responseStr).optInt("code");
                    if (code == 200) {
                        Object t = JSON.parseObject(responseStr, clazz);
                        if (callBack != null) {
                            callBack.onSuccess((T) t);
                        }
                    } else {
                        callBack.onError(code, new JSONObject(responseStr).optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callBack != null) {
                        callBack.onError(500, "Network error");
                    }
                    MangoLog.e(e.getMessage());
                }
            }
        });
    }

    public static void addInterceptor(Interceptor encryptDecryptInterceptor){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(encryptDecryptInterceptor).build();
        MangoHttpUtils.initClient(client);
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

    public static void openLog(boolean open){
        MangoLog.DEBUG = open;
    }
}
