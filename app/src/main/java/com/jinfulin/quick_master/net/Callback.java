package com.jinfulin.quick_master.net;

import org.json.JSONObject;

public interface Callback {
    /**
     * 请求成功的回调
     *
     */
    public void success(String result);

    /**
     * 请求的结果不正确
     *
     * @param jsonObject JSONObject
     * @param message    服务器返回的提示信息
     */
    public void notice(JSONObject jsonObject, String message);

    /**
     * 请求结果为空
     */
//    public void empty();

    /**
     * 请求失败
     *
     * @param e
     */
    public void failed(String e);
}
