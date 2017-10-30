# mangohttp

对okhttp的封装类，okhttp见：[https://github.com/square/okhttp](https://github.com/square/okhttp).

目前对应okhttp版本`3.3.1`.

## 用法

* Android Studio

```
repositories {
    jcenter()
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.carlrocks:mangohttp:1.2'
}
```


## 目前对以下需求进行了封装
* 一般的get请求
* 一般的post请求
* 基于Http Post的文件上传（类似表单）
* 文件下载/加载图片
* 上传下载的进度回调
* 支持自签名网站https的访问，提供方法设置下证书就行


## 请求Log

打开请求日志

```
 MangoLog.DEBUG = true;
```

##其他用法示例

### GET请求

```java
NetRequestParameters parameters = new NetRequestParameters();
parameters.put("token", token);
parameters.put("province", province);
parameters.put("device", device);
MangoHttp.get("http://blog.csdn.net", clzz, parameters, new RequestCallback<AnchorResp>() {
   @Override
   public void done(AnchorResp anchorResp) {
       if(anchorResp != null){

       }
   }

   @Override
   public void onException(NetRequestException exception) {
   }
});
```

### POST请求

```java
NetRequestParameters parameters = new NetRequestParameters();
parameters.put("token", token);
parameters.put("province", province);
parameters.put("device", device);
MangoHttp.post("http://blog.csdn.net", clzz, parameters, new RequestCallback<AnchorResp>() {
   @Override
   public void done(AnchorResp anchorResp) {
       if(anchorResp != null){

       }
   }

   @Override
   public void onException(NetRequestException exception) {
   }
});

```

### Post File

```java
 NetRequestParameters parameters = new NetRequestParameters();
 parameters.put("file", file);
 MangoHttp.post("http://blog.csdn.net", clzz, parameters, new RequestCallback<BaseResp>() {
    @Override
    public void done(BaseResp baseResp) {
        if(baseResp != null){

        }
    }

    @Override
    public void onException(NetRequestException exception) {
    }
 });
```
将文件作为请求体，发送到服务器。

```
### 下载文件

MangoHttp.donwload("http://sw.bos.baidu.com/sw-search-sp/software/532b3c8cc8042/QQ_8.9.4.21584_setup.exe",
                 new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "qq.exe") {
     @Override
     public void onError(Call call, Exception e, int id) {

     }

     @Override
     public void onResponse(File response, int id) {
     }

     @Override
     public void inProgress(float current, long total, int id) {
        //use progress: 0 ~ 1
     }
 });

callback回调中有`inProgress `方法，直接复写即可。
```

## 对于Https

依然是通过配置即可，框架中提供了一个类`HttpsUtils`

* 设置可访问所有的https网站

```
MangoHttp.initSslSocktFactory(null, null, null);
```

* 设置具体的证书

```
MangoHttp.initSslSocktFactory(证书的inputstream, null, null);
```

* 双向认证

```

MangoHttp.initSslSocktFactory(
	证书的inputstream,
	本地证书的inputstream,
	本地证书的密码);
```

同样的，框架中只是提供了几个实现类，你可以自行实现`SSLSocketFactory`，传入sslSocketFactory即可。


```

MangoHttp.initSslSocktFactory(
	SSLSocketFactory sslSocketFactory,
	X509TrustManager trustManager);
```

## 混淆

```
#mangohtt
-dontwarn com.carlrocks.http.**
-keep class com.carlrocks.http.**{*;}


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}


#okio
-dontwarn okio.**
-keep class okio.**{*;}


```






