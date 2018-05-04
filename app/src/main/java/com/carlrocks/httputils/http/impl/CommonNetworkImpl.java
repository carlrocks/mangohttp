package com.carlrocks.httputils.http.impl;

import com.carlrocks.http.okhttp.json.DataResponse;
import com.carlrocks.http.okhttp.json.RequestCallBack;
import com.carlrocks.httputils.entity.ResultResp;
import com.carlrocks.httputils.example.RequestParameters;
import com.carlrocks.httputils.http.BaseNetworkReq;

public class CommonNetworkImpl extends BaseNetworkReq{

    private static final String TAG_GETANCHOR = "currency/api/v1/merchant/verifyMobile";

    public static void testGet(String province, String device, RequestCallBack<DataResponse<ResultResp>> callBack){
        RequestParameters parameters = RequestParameters.getBasicParameters();
        parameters.put("mobile", "+8618627748863");
        postRequest(TAG_GETANCHOR, parameters, callBack);
    }

    public static void testPost(String province, String device, RequestCallBack<DataResponse<ResultResp>> callBack){
        RequestParameters parameters = RequestParameters.getBasicParameters();
        parameters.put("mobile", "+8618627748863");
        postRequest(TAG_GETANCHOR, parameters, callBack);
    }
}
