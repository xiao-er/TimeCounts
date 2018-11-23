package com.xiaoxiao.timecount.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt_minu_sec, txt_sec, txt_hour;
    private TimeCountUtil secTimeCountUtil, minuSecTimeCountUtil, hourMinuSecTimeCountUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        txt_sec = findViewById(R.id.txt_sec);
        txt_minu_sec = findViewById(R.id.txt_minu_sec);
        txt_hour = findViewById(R.id.txt_hour);
        minuSecTimeCountUtil = new TimeCountUtil(MainActivity.this, 3600 * 1000, 1000, txt_minu_sec, 1, "");
        minuSecTimeCountUtil.start();
        secTimeCountUtil = new TimeCountUtil(MainActivity.this, 60 * 1000, 1000, txt_sec, 0, "");
        secTimeCountUtil.start();

        hourMinuSecTimeCountUtil = new TimeCountUtil(MainActivity.this, 36000 * 1000, 1000, txt_hour, 2, "");
        hourMinuSecTimeCountUtil.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (minuSecTimeCountUtil != null) {
            minuSecTimeCountUtil.cancel();
        }
        if (secTimeCountUtil != null) {
            secTimeCountUtil.cancel();
        }
        if (hourMinuSecTimeCountUtil != null) {
            hourMinuSecTimeCountUtil.cancel();
        }

    }
}
