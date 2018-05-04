package com.carlrocks.http.okhttp.json;

import java.io.Serializable;

/**
 *
 */
public class CodeResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public DataResponse toLzyResponse() {
        DataResponse lzyResponse = new DataResponse();
        lzyResponse.code = code;
        lzyResponse.msg = msg;
        return lzyResponse;
    }
}
