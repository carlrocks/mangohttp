package com.carlrocks.http.okhttp;



/**
 * Created by wokoworks on 2017/5/4.
 */

public abstract class PundiCallBack <T> {

    //成功
    public abstract void onSuccess(T t);
    //失败
    public abstract void onError(int code, String msg);

}
