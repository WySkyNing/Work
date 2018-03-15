package com.wy.work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ning.fastwork.net.bass.NetworkUtil;
import com.wy.work.bean.LoginBean;
import com.wy.work.dialog.LoginDialogFragment;
import com.wy.work.dialog.SetNameDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginDialogFragment.LoginInputCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NetworkUtil.getRequest("http://mapp.fengdafenglibao.com/login/noOauth2");
//        http://47.96.20.53/app/account/login
//        jsonObject.put("login",et_phone_number.getText().toString());
//        jsonObject.put("password",et_pass_world.getText().toString());
//        jsonObject.put("uuid", DeviceUtil.getDeviceId(this));
//        jsonObject.put("version",DeviceUtil.getVersionName(this));
//        jsonObject.put("os","Android");
        Map<String,String> map = new HashMap<>();

        map.put("login","18842606495");
        map.put("password","123456");

        NetworkUtil.getInstance().postRequest(this, "http://47.96.20.53/app/account/login",map, new NetworkUtil.RequestCallBack() {
            @Override
            public void onResponse(Object response) {

            }

            @Override
            public void onError() {

            }
        });





        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account","18842606495");
            jsonObject.put("passwd","q1234567");
            jsonObject.put("loginVersionName","Android1.0.0");

            jsonObject.put("captcha","");
            jsonObject.put("sid","");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetworkUtil.getInstance().postJsonRequest(this,"http://mapp.fengdafenglibao.com/login/noOauth2",jsonObject, new NetworkUtil.RequestCallBack<LoginBean>() {
            @Override
            public void onResponse(LoginBean response) {

                Log.e("wy_sid",response.getBody().getSid());

            }

            @Override
            public void onError() {

            }
        });

//        NetworkUtil.getInstance().showLoading(this);

//        Log.e("wy__base64_密文: ", EncryptionUtil.codeToBase64("待加密文字"));
//
//        Log.e("wy__base64_明文: ", EncryptionUtil.decodeToBase64("5b6F5Yqg5a+G5paH5a2X"));
//
//        Log.e("wy__md5_密文: ", EncryptionUtil.codeToMD5("待加密文字"));
//
//        String secretKey = EncryptionUtil.generateKey();
//        String codeToAES = EncryptionUtil.codeToAES("key","aaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//        Log.e("wy__aes_密文: ", codeToAES);
//
//        Log.e("wy__aes_明文: ", EncryptionUtil.decodeToAes("key",codeToAES));

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNameDialogFragment(View view) {

        SetNameDialogFragment setNameDialogFragment = new SetNameDialogFragment();
        setNameDialogFragment.show(getFragmentManager(), "setNameDialogFragment");
    }

    public void loginDialogFragment(View view) {

        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();

        loginDialogFragment.show(getFragmentManager(), "loginDialogFragment");

    }

    @Override
    public void onLoginInputComplete(String userName, String passWorld) {

        Toast.makeText(this, userName + "  " + passWorld, Toast.LENGTH_SHORT).show();
    }

    public void otherActivity(View view) {

        Intent intent = new Intent();
        intent.setClass(this,OtherActivity.class);
        startActivity(intent);
    }
}
