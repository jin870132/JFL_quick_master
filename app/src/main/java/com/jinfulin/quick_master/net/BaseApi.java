package com.jinfulin.quick_master.net;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jinfulin.quick_master.util.AccountManager;
import com.jinfulin.quick_master.util.CustomToast;
import com.jinfulin.quick_master.util.UIUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 封装请求接口的类
 * <p/>
 * Created by king on 15/12/01.
 */
public class BaseApi {

    private static  BaseApi instance = null;

    private Context context;
    private HttpUtils httpUtils = new HttpUtils();
    private int mCode;

    private BaseApi(Context context) {
        this.context = context;
    }

    public static BaseApi getInstance(Context context) {
        if (instance == null) {
            synchronized (NetWork.class) {
                if (instance == null) {
                    instance = new BaseApi(context.getApplicationContext());
                }
            }
        }
        return instance;
    }


    /**
     * 对返回结果先进行预处理，判断返回结果是否合法，合法才进行回调请求成功
     *
     * @return
     */
    public RequestCallBack getCallback(final Callback callback) {
        return new RequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                String data = responseInfo.result.toString();

                try {
                    data = data.replace("null,", "\"\",");// 防止返回的内容出现null，导致解析出错
                    data = data.replace("null}", "\"\"}");
                    data = data.replace("<br/>", "\\n");
                    data = data.replace("<br />", "\\n");
                    if (TextUtils.isEmpty(data)) {
                        callback.failed("未知错误");
                    }
                    JSONObject json = new JSONObject(data);
                    mCode = json.optInt("code");
                    String message = json.optString("message");
                    if (mCode == 1) {
                        callback.success(json.optString("message"));
                    } else {
                        callback.notice(json, message);
                        CustomToast.showToast("提示:" + json.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    mCode = -2;
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callback.failed("错误:" + s);
                CustomToast.showToast("错误:" + s);
            }
        };
    }

    //使用xutils访问网络

    /**
     * GET 请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param callback 结果回调
     */
    public void getByXutls(String url, RequestParams params, Callback callback) {
        params.addHeader("charset", "UTF-8");
        params.addHeader("content-type", "application/json");
        httpUtils.send(HttpRequest.HttpMethod.GET, url, params, getCallback(callback));
    }

    /**
     * POST 请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param callback 结果回调
     */
    public void postByXutls(String url, RequestParams params, Callback callback) {
        params.addHeader("charset", "UTF-8");
        params.addHeader("content-type", "application/json");
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, getCallback(callback));
    }


//--------------------------------------------------------------------------------------------------------

    //使用volley访问网络
    public void get(String url, Map<String, String> params, Callback callback) {
        Map<String, String> defaultParams = new HashMap<String, String>();
        if (params != null) defaultParams.putAll(params);
        String data = encodeQuery(params);
        String tmpUrl = url;
        if (!TextUtils.isEmpty(data)) {
            if (tmpUrl.indexOf("?") > 0) {
                tmpUrl += '&' + data;
            } else {
                tmpUrl += '?' + data;
            }
        }
        RequestQueue mRequestQueue = Volley.newRequestQueue(UIUtils.getContext());
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, tmpUrl, null, getResponseListener(callback), getErrorListener(callback)) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap localHashMap = new HashMap();
                localHashMap.put("Cookie", AccountManager.getInstance().getCookie());
                return localHashMap;
            }
        };
        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    //使用volley访问网络
    public void get(String url, Callback callback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(UIUtils.getContext());
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, getResponseListener(callback), getErrorListener(callback)) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap localHashMap = new HashMap();
                localHashMap.put("Cookie", AccountManager.getInstance().getCookie());
                return localHashMap;
            }
        };
        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    public void post(String url, JSONObject jsonObject, final Callback callback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(UIUtils.getContext());
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.POST, url, jsonObject, getResponseListener(callback), getErrorListener(callback)) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap localHashMap = new HashMap();
                localHashMap.put("Cookie", AccountManager.getInstance().getCookie());
                return localHashMap;
            }
        };
        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    /**
     * login接口需要拿到cookie,与其他不同,所以要重新写一份
     *
     * @param url
     * @param jsonObject
     * @param callback
     */
    public void postLogin(String url, JSONObject jsonObject, final Callback callback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(UIUtils.getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, getResponseListener(callback), getErrorListener(callback)) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    AccountManager.getInstance().setCookie(rawCookies);
                    String dataString = new String(response.data, "UTF-8");
                    JSONObject object = new JSONObject(dataString);
                    return Response.success(object, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        mRequestQueue.add(request);
        mRequestQueue.start();
    }


    public Response.ErrorListener getErrorListener(final Callback callback) {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError.networkResponse == null) {
                    callback.failed("错误:" + volleyError.toString());
                } else {
                    byte[] htmlBodyBytes = volleyError.networkResponse.data;
                    String errorInfo = null;
                    try {
                        errorInfo = new String(htmlBodyBytes, "utf-8");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (!TextUtils.isEmpty(errorInfo)) {
                        errorInfo = errorInfo.replace("null,", "\"\",");// 防止返回的内容出现null，导致解析出错
                        errorInfo = errorInfo.replace("null}", "\"\"}");
                        errorInfo = errorInfo.replace("<br/>", "\\n");
                        errorInfo = errorInfo.replace("<br />", "\\n");
                        callback.failed("错误:" + errorInfo);
                        if (errorInfo.equals("{\"code\":0,\"message\":\"登录超时\"}")) {
                            CustomToast.showToast("登录超时,请重新登录");
                            AccountManager.goLogingActivity();
                        }
                    } else {
                        callback.failed("错误:" + volleyError.toString());
                    }
                }
            }
        };
        return errorListener;
    }

    public Response.Listener<JSONObject> getResponseListener(final Callback callback) {
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String data = jsonObject.toString();
                try {
                    data = data.replace("null,", "\"\",");// 防止返回的内容出现null，导致解析出错
                    data = data.replace("null}", "\"\"}");
                    data = data.replace("<br/>", "\\n");
                    data = data.replace("<br />", "\\n");
                    if (TextUtils.isEmpty(data)) {
                        callback.failed("未知错误");
                    }
                    JSONObject json = new JSONObject(data);
                    mCode = json.optInt("code");
                    String message = json.optString("message");
                    if (mCode == 1) {
                        callback.success(json.optString("message"));
                    } else {
                        callback.notice(json, message);
//                        CustomToast.showToast("提示:" + json.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    mCode = -2;
                }
            }
        };
        return responseListener;
    }

    /**
     * 将数据编码成http-query的形式的字符串
     *
     * @param parameters
     * @return
     */
    private String encodeQuery(Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String key : parameters.keySet()) {
            if (key == null)
                continue;
            String value = parameters.get(key);
            if (value == null)
                continue;
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            try {
                sb.append(URLEncoder.encode(key, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(parameters.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String encodeQuery = sb.toString();
        return encodeQuery;
    }
    //--------------------------------------------------------------------------------------------------------

}
