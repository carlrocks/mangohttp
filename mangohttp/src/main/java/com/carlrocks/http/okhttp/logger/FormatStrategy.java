package com.carlrocks.http.okhttp.logger;

public interface FormatStrategy {

  void log(int priority, String tag, String message);
}
