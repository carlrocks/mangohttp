package com.carlrocks.http.okhttp.builder;

import com.carlrocks.http.okhttp.RequestHeaderParameters;
import com.carlrocks.http.okhttp.request.PostStringRequest;
import com.carlrocks.http.okhttp.request.RequestCall;

import com.carlrocks.http.okhttp.utils.MangoLog;
import okhttp3.MediaType;

import java.util.Map;

/**
 * Created by zhy on 15/12/14.
 */
public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder>
{
    private String content;
    private MediaType mediaType;


    public PostStringBuilder content(String content)
    {
        this.content = content;
        MangoLog.i("===PosString===" + url + "  【jsonString】:" + content);
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    public PostStringBuilder addRequestHeaders(final RequestHeaderParameters headers)
    {
        headers(headers.getParams());
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostStringRequest(url, tag, params, headers, content, mediaType,id).build();
    }


}
