package com.carlrocks.http.okhttp;

import java.util.LinkedHashMap;

/**
 * @author carl
 */
public class RequestHeaderParameters {

	private LinkedHashMap<String, String> mParams = new LinkedHashMap<>();

	public LinkedHashMap<String, String> getParams() {
		return this.mParams;
	}

	public void setParams(LinkedHashMap<String, String> params) {
		this.mParams = params;
	}

	public void addHeader(String key, String val) {
		this.mParams.put(key, val);
	}

	public void addHeader(String key, int value) {
		this.mParams.put(key, String.valueOf(value));
	}

	public void addHeader(String key, long value) {
		this.mParams.put(key, String.valueOf(value));
	}
}
