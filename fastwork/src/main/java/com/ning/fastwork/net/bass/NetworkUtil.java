package com.ning.fastwork.net.bass;

import android.content.Context;
import com.google.gson.Gson;
import com.ning.fastwork.loading.MProgressDialog;
import com.ning.fastwork.net.okhttp.OkHttpUtils;
import com.ning.fastwork.net.okhttp.callback.StringCallback;
import org.json.JSONObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by Administrator on 2018/1/25/0025.
 */

public class NetworkUtil {

    private MProgressDialog mProgressDialog;

    private NetworkUtil() {

    }

    private static class SingleHolder {

        private static final NetworkUtil netWorkSingle = new NetworkUtil();
    }

    public static NetworkUtil getInstance() {
        return SingleHolder.netWorkSingle;
    }


    public  void getRequest(Context context,String url) {

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {


                    }
                });
    }

    /**
     * 参数为 json 格式的网络请求
     * @param context loading 需要的 context
     * @param url 网络请求的地址
     * @param mapParams json 字符串格式的请求参数
     * @param requestCallBack 请求的回调
     * @param <T> 对应的实体类类型,继承了 BaseBean
     */
    public <T> void postRequest(Context context, String url, Map<String,String>  mapParams, final RequestCallBack<T> requestCallBack) {

        showLoading(context);

        OkHttpUtils
                .post()
                .url(url)
                .params(mapParams)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        dismissDialog();

                        e.printStackTrace();

                        requestCallBack.onError();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        dismissDialog();

                        //Log.e("wy__type1",requestCallBack.getClass().getGenericSuperclass().toString());
                        // Log.e("wy__type2",requestCallBack.getClass().getGenericInterfaces()[0].toString());

                        //通过类对象获取它的泛型类型
                        ParameterizedType parameterizedType = (ParameterizedType) requestCallBack.getClass().getGenericInterfaces()[0];
                        Type type[] = parameterizedType.getActualTypeArguments();

                        T bean = new Gson().fromJson(response, type[0]);

                        requestCallBack.onResponse(bean);
                    }
                });
    }

    /**
     * 参数为 json 格式的网络请求
     * @param context loading 需要的 context
     * @param url 网络请求的地址
     * @param jsonParams json 字符串格式的请求参数
     * @param requestCallBack 请求的回调
     * @param <T> 对应的实体类类型,继承了 BaseBean
     */
    public <T extends BaseBean> void postJsonRequest(Context context,String url, JSONObject jsonParams, final RequestCallBack<T> requestCallBack) {

        showLoading(context);

        OkHttpUtils
                .postString()
                .url(url)
                .content(jsonParams.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        dismissDialog();

                        e.printStackTrace();

                        requestCallBack.onError();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        dismissDialog();

                        //Log.e("wy__type1",requestCallBack.getClass().getGenericSuperclass().toString());
                        // Log.e("wy__type2",requestCallBack.getClass().getGenericInterfaces()[0].toString());

                        //通过类对象获取它的泛型类型
                        ParameterizedType parameterizedType = (ParameterizedType) requestCallBack.getClass().getGenericInterfaces()[0];
                        Type type[] = parameterizedType.getActualTypeArguments();

                        T bean = new Gson().fromJson(response, type[0]);

                        requestCallBack.onResponse(bean);
                    }
                });


    }


    /**
     * 显示 loading
     * @param context context
     */
    public  void showLoading(Context context){

        if (mProgressDialog ==null){

            mProgressDialog = new MProgressDialog(context);

        }

        mProgressDialog.show();
    }

    /**
     * 消失 loading
     */
    public void dismissDialog(){

        if (mProgressDialog != null){

            mProgressDialog.dismiss();

        }

        mProgressDialog = null;
    }

    /**
     * 网络请求的回调接口
     * @param <T> 对应的实体类类型
     */
    public interface RequestCallBack<T> {

        void onResponse(T response);

        void onError();

    }


}
