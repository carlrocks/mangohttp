package com.carlrocks.http.okhttp.builder;

import com.carlrocks.http.okhttp.MangoHttpUtils;
import com.carlrocks.http.okhttp.request.OtherRequest;
import com.carlrocks.http.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, MangoHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
