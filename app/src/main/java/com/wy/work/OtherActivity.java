package com.wy.work;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ning.fastwork.net.bass.NetworkUtil;

/**
 * Created by Administrator on 2018/3/15.
 */

public class OtherActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);




        //获取单例对象，退出Activity即可模拟出内存泄露
//        TestManager testManager = TestManager.getInstance(this);
    }


    public void showDialog(View view) {

        Handler handler = new Handler();

        NetworkUtil.getInstance().showLoading(OtherActivity.this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NetworkUtil.getInstance().showLoading(OtherActivity.this);

            }
        },1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NetworkUtil.getInstance().showLoading(OtherActivity.this);

                NetworkUtil.getInstance().dismissDialog();


            }
        },1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NetworkUtil.getInstance().showLoading(OtherActivity.this);

                NetworkUtil.getInstance().dismissDialog();


            }
        },1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NetworkUtil.getInstance().showLoading(OtherActivity.this);

            }
        },1000);






        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NetworkUtil.getInstance().dismissDialog();

            }
        },5000);

    }

    public void dismissDialog(View view) {


    }
}
