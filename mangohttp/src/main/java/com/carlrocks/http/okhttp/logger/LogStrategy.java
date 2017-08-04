package com.carlrocks.http.okhttp.logger;

public interface LogStrategy {

  void log(int priority, String tag, String message);
}
