package com.carlrocks.http.okhttp;


import com.carlrocks.http.okhttp.exception.NetRequestException;

public abstract class RequestCallback<T> {

	public abstract void done(T t);

	public abstract void onException(NetRequestException exception);
}
