package com.ning.fastwork.net.bass;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ning.fastwork.loading.MProgressDialog;
import com.ning.fastwork.net.BaseHttpRequestCallback;
import com.ning.fastwork.net.HttpRequest;
import com.ning.fastwork.net.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


/**
 * Created by Administrator on 2018/1/25/0025.
 */

public class NetWorkSingle {

    private MProgressDialog mMProgressDialog;

    private NetWorkSingle() {

    }

    private static class SingleHolder {

        private static final NetWorkSingle netWorkSingle = new NetWorkSingle();
    }

    public static NetWorkSingle getInstance() {
        return SingleHolder.netWorkSingle;
    }

    public void post(final String url, JSONObject jsonObject, final Context context, final Class clazz, final RequestListener requestListener) {

        mMProgressDialog = new MProgressDialog(context);


        //TODO 暂时用 RequestParams 装载参数,本质是JSON
        RequestParams requestParams = new RequestParams();
//        requestParams.addFormDataPart("phone", "18842606495");
//        requestParams.addFormDataPart("password","123456789");

//        for(String key : paramsMap.keySet()){
//
//            requestParams.addFormDataPart(key,paramsMap.get(key));
//        }

        Iterator<String> iterator = jsonObject.keys();

        String jsonName;

        while (iterator.hasNext()) {

            jsonName = iterator.next();

            try {

                requestParams.addFormDataPart(jsonName, jsonObject.getString(jsonName));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.e("wy__url:", url);
        Log.e("wy__参数:", new Gson().toJson(requestParams));


        HttpRequest.post(url, requestParams, new BaseHttpRequestCallback() {

            @Override
            protected void onSuccess(Object result) {

                Log.e("wy__", "11111");
                Log.e("wy__成功", context.getClass().getSimpleName() + ": " + url + new Gson().toJson(result));

                if (null != clazz){

                    requestListener.onSuccess(new Gson().fromJson(result.toString(), clazz));
                }else {

                    requestListener.onSuccess(result);
                }


            }

            @Override
            public void onFailure(int errorCode, String msg) {

                Log.e("wy__", "2222");

                requestListener.onFailure(errorCode, msg);

                Log.e("wy__失败", context.getClass().getSimpleName() + ": " + url + "\n" + "  " + errorCode + "   " + msg);

                Log.e("wy__", ":  " + (Looper.getMainLooper().getThread() == Thread.currentThread()));


                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onStart() {

                mMProgressDialog.show();
            }

            @Override
            public void onFinish() {

                mMProgressDialog.dismiss();
            }
        });


    }


    public interface RequestListener<T> {

        void onSuccess(T result);

        void onFailure(int errorCode, String errorMessage);
    }


}
