package com.carlrocks.httputils.http.impl;

import com.carlrocks.http.okhttp.callback.FileCallBack;
import com.carlrocks.httputils.http.BaseNetworkReq;

public class DownloadFileImpl extends BaseNetworkReq {

    public static void donloadFile(String url, FileCallBack callBack){
        download(url, callBack);
    }
}
