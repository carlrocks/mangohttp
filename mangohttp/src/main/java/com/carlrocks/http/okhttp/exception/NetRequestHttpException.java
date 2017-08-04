
package com.carlrocks.http.okhttp.exception;

/**
 *Title: HTTP 请求时，如果发生异常，则以该异常进行抛出<br>
 *Description: <br>
 *Create Date: 2014-9-12上午9:58:11<br>
 *
 *@author dingzai_lino
 */
public class NetRequestHttpException extends NetRequestException {
	
    private static final long serialVersionUID = 1L;
    
    /** HTTP请求出错时，服务器返回的错误状态码 */
    private final int mStatusCode;

    /**
     * 构造函数。
     * 
     * @param message    HTTP请求出错时，服务器返回的字符串
     * @param statusCode HTTP请求出错时，服务器返回的错误状态码
     */
    public NetRequestHttpException(String message, int statusCode) {
        super(message);
        mStatusCode = statusCode;
    }
    
    /**
     * HTTP请求出错时，服务器返回的错误状态码。
     * 
     * @return 服务器返回的错误状态码
     */
    public int getStatusCode() {
        return mStatusCode;
    }
}
