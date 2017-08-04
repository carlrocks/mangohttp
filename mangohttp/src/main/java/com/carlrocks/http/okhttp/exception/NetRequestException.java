package com.carlrocks.http.okhttp.exception;

/**
 *Title: 异常处理类<br>
 *Description: <br>
 *Create Date: 2014-9-12上午9:57:55<br>
 *
 *@author dingzai_lino
 */
public class NetRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NetRequestException() {
	}

	public NetRequestException(String message) {
		super(message);
	}

	public NetRequestException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NetRequestException(Throwable throwable) {
		super(throwable);
	}
}
