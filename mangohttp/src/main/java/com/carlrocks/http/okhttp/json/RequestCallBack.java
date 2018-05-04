package com.carlrocks.http.okhttp.json;

import com.carlrocks.http.okhttp.callback.Callback;
import com.carlrocks.http.okhttp.utils.MangoLog;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class RequestCallBack<T> extends Callback<T> {


    /**onSuccess*/
    public abstract void onSuccess(T response);

    /**onError*/
    public abstract void onError(final int code, final String msg);

    private Type type;

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(BUFFER_SIZE);
        Buffer buffer = source.buffer();
        String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));
        MangoLog.json(responseBodyString);
        Type genType = getClass().getGenericSuperclass();
        type = ((ParameterizedType) genType).getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            return parseParameterizedType(response, (ParameterizedType) type);
        } else if (type instanceof Class) {
            return parseClass(response, (Class<?>) type);
        } else {
            return parseType(response, type);
        }
    }

    final static int BUFFER_SIZE = 4096;

    /**
     * 将InputStream转换成String
     * @param in InputStream
     * @return String
     * @throws Exception
     *
     */
    public static String InputStreamTOString(InputStream in) throws Exception{

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while((count = in.read(data,0,BUFFER_SIZE)) != -1) {
            outStream.write(data, 0, count);
        }
        return new String(outStream.toByteArray(),"ISO-8859-1");
    }

    @Override
    public void onResponse(T response, int id) {
        onSuccess(response);
    }


    @Override
    public void onError(Call call, Exception e, int id) {
        String error = e.getMessage();
        if(isGoodJson(error)){
            CodeResponse simpleResponse = Convert.fromJson(e.getMessage(), CodeResponse.class);
            onError(simpleResponse.code,simpleResponse.msg);
        }else {
            onError(-1,"network error");
        }
        MangoLog.i(e.toString());
    }

    private T parseClass(Response response, Class<?> rawType) throws Exception {
        if (rawType == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType == String.class) {
            //noinspection unchecked
            return (T) body.string();
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(body.string());
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(body.string());
        } else {
            T t = Convert.fromJson(jsonReader, rawType);
            response.close();
            return t;
        }
    }

    private T parseType(Response response, Type type) throws Exception {
        if (type == null){
            return null;
        }
        ResponseBody body = response.body();
        if (body == null){
            return null;
        }
        JsonReader jsonReader = new JsonReader(body.charStream());

        // 泛型格式如下： new RequestCallBack<任意JavaBean>(this)
        T t = Convert.fromJson(jsonReader, type);
        response.close();
        return t;
    }

    private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
        if (type == null){
            return null;
        }
        ResponseBody body = response.body();
        if (body == null){
            return null;
        }
        JsonReader jsonReader = new JsonReader(body.charStream());
        Type rawType = type.getRawType();                     // 泛型的实际类型
        Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数
        if (rawType != DataResponse.class) {
            T t = Convert.fromJson(jsonReader, type);
            response.close();
            return t;
        } else {
            if (typeArgument == Void.class) {
                // 泛型格式如下： new RequestCallBack<LzyResponse<Void>>(this)
                CodeResponse simpleResponse = Convert.fromJson(jsonReader, CodeResponse.class);
                response.close();
                //noinspection unchecked
                return (T) simpleResponse.toLzyResponse();
            } else {
                // 泛型格式如下： new RequestCallBack<LzyResponse<内层JavaBean>>(this)
                DataResponse dataResponse = Convert.fromJson(jsonReader, type);
                response.close();
                int code = dataResponse.code;
                //这里的0是以下意思
                //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改
               if (code == 200) {
                    //noinspection unchecked
                    return (T) dataResponse;
               }
               else {
                    //直接将服务端的错误信息抛出，onError中可以获取  1004,1005  "已在其他设备上登陆"
                    JSONObject jsonObject = new  JSONObject();
                    jsonObject.put("code", code);
                    jsonObject.put("msg", dataResponse.msg);
                    throw new IllegalStateException(jsonObject.toString());
                }
            }
        }
    }

    public boolean isGoodJson(String json) {

        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }
}
