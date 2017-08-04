package com.carlrocks.httputils.http.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Utils {

    public static String toString(Object obj) {
        if (null == obj) {
            return "null";
        }
        return obj.getClass().getName()
                + "@"
                + Integer.toHexString(obj.hashCode())
                + JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.PrettyFormat);
    }
}
