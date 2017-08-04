package com.carlrocks.http.okhttp;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class NetRequestParameters {
	private static final String DEFAULT_CHARSET = "UTF-8";
	private LinkedHashMap<String, Object> mParams = new LinkedHashMap<>();

	public LinkedHashMap<String, Object> getParams() {
		return this.mParams;
	}

	public void setParams(LinkedHashMap<String, Object> params) {
		this.mParams = params;
	}
	
	public void put(String key, String val) {
		this.mParams.put(key, val);
	}

	public void put(String key, int value) {
		this.mParams.put(key, String.valueOf(value));
	}

	public void put(String key, long value) {
		this.mParams.put(key, String.valueOf(value));
	}

	public void put(String key, Bitmap bitmap) {
		this.mParams.put(key, bitmap);
	}
	
	public void put(String key, File file) {
		this.mParams.put(key, file);
	}

	public void put(String key, Object val) {
		this.mParams.put(key, val.toString());
	}

	public Object get(String key) {
		return this.mParams.get(key);
	}

	public void remove(String key) {
		if (this.mParams.containsKey(key)) {
			this.mParams.remove(key);
			this.mParams.remove(this.mParams.get(key));
		}
	}

	public Set<String> keySet() {
		return this.mParams.keySet();
	}

	public boolean containsKey(String key) {
		return this.mParams.containsKey(key);
	}

	public boolean containsValue(String value) {
		return this.mParams.containsValue(value);
	}

	public int size() {
		return this.mParams.size();
	}

	public String encodeUrl() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		TreeMap<String, Object> treeParams = sortMapByKey(mParams);
		for (String key : treeParams.keySet()) {
			if (first)
				first = false;
			else {
				sb.append("&");
			}
			Object value = treeParams.get(key);
			if (value instanceof String) {
				String param = (String) value;
				sb.append(key + "=" + param);
			}else{
				sb.append(key + "=" + "");
			}
		}
		return sb.toString();
	}

	public String encodeUrlData(String url) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		TreeMap<String, Object> treeParams = sortMapByKey(mParams);
		for (String key : treeParams.keySet()) {
			if (first)
				first = false;
			else {
				sb.append("&");
			}
			Object value = treeParams.get(key);
			if (value instanceof String) {
				String param = (String) value;
				try {
					sb.append(key + "=" + URLEncoder.encode(param, DEFAULT_CHARSET));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}else{
				sb.append(key + "=" + "");
			}
		}
		return sb.toString();
	}

	public String encodeNewUrl(String requestType) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		TreeMap<String, Object> treeParams = sortMapByKey(mParams);
		for (String key : treeParams.keySet()) {
			if (first)
				first = false;
			else {
				sb.append("&");
			}
			Object value = treeParams.get(key);
			if (value instanceof String) {
				String param = (String) value;
				sb.append(key + "=" + param);
			}else{
				sb.append(key + "=" + "");
			}
		}
		return requestType + "?" + sb.toString();
	}

	public TreeMap<String, Object> sortMapByKey(Map<String, Object> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return new TreeMap<>();
		}
		TreeMap<String, Object> sortedMap = new TreeMap<>();
		sortedMap.putAll(oriMap);
		return sortedMap;
	}

	public boolean hasBinaryData() {
		Set<String> keys = this.mParams.keySet();
		for (String key : keys) {
			Object value = this.mParams.get(key);
			
			if ((value instanceof ByteArrayOutputStream)
					|| (value instanceof File)
					|| (value instanceof Bitmap)) {
				return true;
			}
		}
		return false;
	}
}
