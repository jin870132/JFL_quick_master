package com.jinfulin.quick_master.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jinfulin.quick_master.beans.ConfigInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2015/12/09.
 */
public class ConfigManager {

    private Gson gson;

    private static ConfigManager sInstance;
    private JSONObject configObj;

    private ConfigManager() {
        gson = new Gson();
    }

    public static ConfigManager getInstance() {
        if (sInstance == null) {
            synchronized (ConfigManager.class) {
                if (sInstance == null)
                    sInstance = new ConfigManager();

            }
        }
        return sInstance;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public JSONObject getConfig() {
        return configObj;
    }

    /**
     * 设置用户信息
     *
     * @param configStr config的json字符串
     */
    public void setConfig(String configStr) {
        try {
            JSONObject configObj = new JSONObject(configStr);
            this.configObj = configObj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得场地列表
     * @return
     */
    public ArrayList<ConfigInfo.Course> getCourseList() {
        String result = null;
        try {
            result = configObj.getString("course");
            ArrayList<ConfigInfo.Course> appointInfoList = gson.fromJson(
                    result,
                    new TypeToken<List<ConfigInfo.Course>>() {
                    }.getType());
            return appointInfoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得会员卡类型
     * @return
     */
    public ArrayList<ConfigInfo.Cardtype> getcardtypeList() {
        String result = null;
        try {
            result = configObj.getString("cardtype");
            ArrayList<ConfigInfo.Cardtype> acrdtypeList = gson.fromJson(
                    result,
                    new TypeToken<List<ConfigInfo.Cardtype>>() {
                    }.getType());
            return acrdtypeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得身份类型
     * @return
     */
    public ArrayList<ConfigInfo.Identity> getidentityList() {
        String result = null;
        try {
            result = configObj.getString("identity");
            ArrayList<ConfigInfo.Identity> list = gson.fromJson(
                    result,
                    new TypeToken<List<ConfigInfo.Identity>>() {
                    }.getType());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获得营业点列表
     * @return
     */
    public ArrayList<ConfigInfo.Outlet> getoutletList() {
        String result = null;
        try {
            result = configObj.getString("outlet");
            ArrayList<ConfigInfo.Outlet> list = gson.fromJson(
                    result,
                    new TypeToken<List<ConfigInfo.Outlet>>() {
                    }.getType());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
