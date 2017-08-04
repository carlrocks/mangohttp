package com.carlrocks.httputils.http.impl;

import com.carlrocks.http.okhttp.NetRequestParameters;
import com.carlrocks.http.okhttp.RequestCallback;
import com.carlrocks.httputils.entity.UploadResp;
import com.carlrocks.httputils.entity.AnchorResp;
import com.carlrocks.httputils.http.BaseNetworkReq;

import java.io.File;

public class CommonNetworkImpl extends BaseNetworkReq{

    private static final String TAG_GETANCHOR = "getAnchorListTest";

    public static void testGet(String token, String province, String device, RequestCallback<AnchorResp> callBack){
        NetRequestParameters parameters = new NetRequestParameters();
        parameters.put("token", token);
        parameters.put("province", province);
        parameters.put("device", device);
        commonGetRequest(TAG_GETANCHOR, AnchorResp.class, parameters, callBack);
    }

    public static void testPost(String token, String province, String device, RequestCallback<AnchorResp> callBack){
        NetRequestParameters parameters = new NetRequestParameters();
        parameters.put("token", token);
        parameters.put("province", province);
        parameters.put("device", device);
        commonRequest(TAG_GETANCHOR, AnchorResp.class, parameters, callBack);
    }
}
