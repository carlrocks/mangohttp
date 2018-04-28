package com.carlrocks.httputils.example;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.carlrocks.http.okhttp.PundiCallBack;
import com.carlrocks.http.okhttp.callback.FileCallBack;
import com.carlrocks.http.okhttp.utils.MangoLog;
import com.carlrocks.httputils.entity.ResultResp;
import com.carlrocks.httputils.http.impl.CommonNetworkImpl;
import com.carlrocks.httputils.http.impl.DownloadFileImpl;
import okhttp3.Call;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView tvGetView;
    private TextView tvPostView;
    private TextView tvDownloadView;

    private ProgressBar tvGetProgress;
    private ProgressBar tvPostProgress;
    private ProgressBar tvDownloadProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MangoLog.DEBUG = true;

        findViewById(R.id.btn_request_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestGET();
            }
        });
        findViewById(R.id.btn_request_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPost();
            }
        });
        findViewById(R.id.btn_request_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testDonwloadFile();
            }
        });

        tvGetView = (TextView) findViewById(R.id.edit_get);
        tvPostView = (TextView) findViewById(R.id.edit_post);
        tvDownloadView = (TextView) findViewById(R.id.edit_download);

        tvGetProgress = (ProgressBar) findViewById(R.id.get_loading_bar);
        tvPostProgress = (ProgressBar) findViewById(R.id.post_loading_bar);
        tvDownloadProgress = (ProgressBar) findViewById(R.id.download_loading_bar);
        tvDownloadProgress.setMax(100);
    }

    private void requestGET(){
        tvGetProgress.setVisibility(View.VISIBLE);
        final long current = System.currentTimeMillis();
        CommonNetworkImpl.testGet("热门", "MIX", new PundiCallBack<ResultResp>() {
            @Override
            public void onSuccess(ResultResp anchorResp) {
                if(anchorResp != null){
                    String data = anchorResp.getData();
                    Log.i("data====>", data);
                    tvGetProgress.setVisibility(View.GONE);
                    tvGetView.setText("请求时间："+(System.currentTimeMillis() - current+"s"));
                }
            }

            @Override
            public void onError(int code, String msg) {

            }
        });
    }

    private void requestPost(){
        tvPostProgress.setVisibility(View.VISIBLE);
        final long current = System.currentTimeMillis();
        CommonNetworkImpl.testPost("热门", "MIX", new PundiCallBack<ResultResp>() {
            @Override
            public void onSuccess(ResultResp anchorResp) {
                if(anchorResp != null){
                    String data = anchorResp.getData();
                    Log.i("data====>", data);
                    tvPostProgress.setVisibility(View.GONE);
                    tvPostView.setText("请求时间："+(System.currentTimeMillis() - current+"s"));
                }
            }

            @Override
            public void onError(int code, String msg) {
                Log.i("====", "msg");
            }
        });
    }

    private void testDonwloadFile(){
        DownloadFileImpl.donloadFile("http://sw.bos.baidu.com/sw-search-sp/software/532b3c8cc8042/QQ_8.9.4.21584_setup.exe",
                new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "qq.exe") {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(File response, int id) {
                MangoLog.i("onResponse:" + response.getAbsolutePath());
                tvDownloadView.setText("download finished");
            }

            @Override
            public void inProgress(float current, long total, int id) {
                int porgress = (int) (current * 100);
                tvDownloadView.setText("progress:100/" + porgress + "%");
                tvDownloadProgress.setProgress(porgress);
            }
        });

    }
}
