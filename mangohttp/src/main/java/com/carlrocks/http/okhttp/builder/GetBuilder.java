package com.carlrocks.http.okhttp.builder;

import android.net.Uri;

import com.carlrocks.http.okhttp.request.GetRequest;
import com.carlrocks.http.okhttp.request.RequestCall;
import com.carlrocks.http.okhttp.utils.MangoLog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhy on 15/12/14.
 */
public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> implements HasParamsable
{

    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public RequestCall build()
    {
        if (params != null)
        {
            url = appendParams(url, params);
        }

        return new GetRequest(url, tag, params, headers,id).build();
    }

    protected String appendParams(String url, Map<String, String> params)
    {
        if (url == null || params == null || params.isEmpty())
        {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }

    @Override
    public GetBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    public GetBuilder paramsData(Map<String, Object> params)
    {
        StringBuilder sb = new StringBuilder();
        TreeMap<String, Object> treeParams = sortMapByKey(params);
        boolean first = true;
        Map<String, String> paramecia = new LinkedHashMap<>();
        for (String key : treeParams.keySet()) {
            if (first)
                first = false;
            else {
                sb.append("&");
            }
            Object value = params.get(key);
            if (value instanceof String) {
                String param = (String) value;
                paramecia.put(key, param);
                try {
                    sb.append(key + "=" + URLEncoder.encode(param, DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        MangoLog.i("===get==="+url + "?" + sb.toString());
        this.params = paramecia;
        return this;
    }

    public TreeMap<String, Object> sortMapByKey(Map<String, Object> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return new TreeMap<>();
        }
        TreeMap<String, Object> sortedMap = new TreeMap<>();
        sortedMap.putAll(oriMap);
        return sortedMap;
    }

    @Override
    public GetBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }


}
