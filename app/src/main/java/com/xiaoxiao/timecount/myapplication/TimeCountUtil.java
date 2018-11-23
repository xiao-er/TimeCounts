package com.xiaoxiao.timecount.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

/**
 * @author: 潇潇
 * @create on:  2018/11/21
 * @describe:DOTO
 */

public class TimeCountUtil extends CountDownTimer {

    private int type = 0;//倒计时类型
    private OverTime overTime;
    private Context mActivity;
    private String content;
    public TextView txt_timer;

    public TextView getTxt_timer() {
        return txt_timer;
    }

    public void setTxt_timer(TextView txt_timer) {
        this.txt_timer = txt_timer;
    }


    public TimeCountUtil(Context mActivity, long millisInFuture, long countDownInterval, TextView txt_timer, int type, String content) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.txt_timer = txt_timer;
        this.type = type;
        this.content = content;
        txt_timer.setTextColor(Color.BLACK);
    }


    @Override
    public void onTick(long millisUntilFinished) {
        if (type == 0) {
            txt_timer.setClickable(false);//设置不能点击
            txt_timer.setText("（实例倒计时发送验证码）秒：" + millisUntilFinished / 1000 + "S");//设置倒计时时间
            Spannable span = new SpannableString(txt_timer.getText().toString());//获取按钮的文字
//            span.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.colorPrimary)), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//倒计时时间显示为主题色
            txt_timer.setText(span);
        } else if (type == 1) {
            txt_timer.setText("（倒计时领取金币）分秒：" + formatTime(millisUntilFinished));
        } else if (type == 2) {
            txt_timer.setText("（倒计时领取金币）时分秒：" + FormatMiss(millisUntilFinished));

        }


    }

    @Override
    public void onFinish() {
        if (type == 0) {
            txt_timer.setText("重新获取");
            txt_timer.setClickable(true);//重新获得点击
            this.start();
        } else if (type == 1) {
            txt_timer.setText("重新获取");
            overTime.overTiem();
        } else if (type == 2) {
            txt_timer.setText("重新获取");
            overTime.overTiem();
        }
    }

    public void setOverTime(OverTime overTime) {
        this.overTime = overTime;
    }


    public interface OverTime {
        void overTiem();
    }

    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        } else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }

    // 将秒转化成小时分钟秒
    public String FormatMiss(long millisec) {
        int hour = 00;
        int minute = 00;
        int second = (int) millisec / 1000;
        String resultStr = "";
        if (second > 60) {
            minute = second / 60;//取整
            second = second % 60;//取余
            if (minute > 60) {
                hour = minute / 60;
                minute = minute % 60;
                if (hour < 10) {
                    if (minute < 10) {
                        if (second<10){
                            resultStr = "0" + hour + ":" + "0" + minute + ":" +"0"+ second;
                        }else {
                            resultStr = "0" + hour + ":" + "0" + minute + ":" + second;
                        }
                    } else {
                        if (second<10){
                            resultStr = "0" + hour + ":" + minute + ":"+"0" + second;
                        }else {
                            resultStr = "0" + hour + ":" + minute + ":" + second;
                        }

                    }
                } else {
                    if (minute < 10) {
                        if (second<10){
                            resultStr = hour + ":" + "0" + minute + ":"+"0" + second;
                        }else {
                            resultStr = hour + ":" + "0" + minute + ":" + second;
                        }
                    } else {
                        if (second<10){
                            resultStr = hour + ":" + minute + ":" +"0"+ second;
                        }else {
                            resultStr = hour + ":" + minute + ":" + second;
                        }

                    }
                }
            }
        } else {
            if (second < 10) {
                resultStr = hour + ":" + minute + "0" + ":" + second;
            } else {
                resultStr = hour + ":" + minute + ":" + second;
            }
        }
        return resultStr;
    }


}
