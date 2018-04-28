package com.carlrocks.http.okhttp.builder;

import com.carlrocks.http.okhttp.RequestHeaderParameters;
import com.carlrocks.http.okhttp.request.PostFormRequest;
import com.carlrocks.http.okhttp.request.RequestCall;
import com.carlrocks.http.okhttp.utils.MangoLog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhy on 15/12/14.
 */
public class PostFormBuilder extends OkHttpRequestBuilder<PostFormBuilder> implements HasParamsable
{
    private static final String DEFAULT_CHARSET = "UTF-8";
    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build()
    {
        return new PostFormRequest(url, tag, params, headers, files,id).build();
    }

    public PostFormBuilder files(String key, Map<String, File> files)
    {
        for (String filename : files.keySet())
        {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file)
    {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput
    {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file)
        {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString()
        {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }



    @Override
    public PostFormBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    public PostFormBuilder addRequestHeaders(final RequestHeaderParameters headers)
    {
        MangoLog.i("requestheaders:" + headers.getParams().toString());
        headers(headers.getParams());
        return this;
    }

    public PostFormBuilder addRequestParams(Map<String, Object> params)
    {
        StringBuilder sb = new StringBuilder();
        TreeMap<String, Object> treeParams = sortMapByKey(params);
        Map<String, String> paramecia = new LinkedHashMap<>();
        boolean first = true;
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
            }else if(value instanceof File){
                File file = (File) value;
                addFile(key, file.getName(), (File) value);
            }
        }
        MangoLog.i("===post==="+ url + "?" + sb.toString());
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
    public PostFormBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }




}
