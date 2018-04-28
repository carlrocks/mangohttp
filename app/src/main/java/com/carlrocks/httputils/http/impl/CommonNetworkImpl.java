package com.carlrocks.httputils.http.impl;

import com.carlrocks.http.okhttp.PundiCallBack;
import com.carlrocks.httputils.entity.ResultResp;
import com.carlrocks.httputils.example.RequestParameters;
import com.carlrocks.httputils.http.BaseNetworkReq;

public class CommonNetworkImpl extends BaseNetworkReq{

    private static final String TAG_GETANCHOR = "api/exchangeToken";

    public static void testGet(String province, String device, PundiCallBack<ResultResp> callBack){
        RequestParameters parameters = RequestParameters.getBasicParameters();
        parameters.put("province", province);
        parameters.put("device", device);
        commonGetRequest(TAG_GETANCHOR, ResultResp.class, parameters, callBack);
    }

    public static void testPost(String province, String device, PundiCallBack<ResultResp> callBack){
        RequestParameters parameters = RequestParameters.getBasicParameters();
        parameters.put("province", province);
        parameters.put("device", device);
        commonRequest(TAG_GETANCHOR, ResultResp.class, parameters, callBack);
    }
}
